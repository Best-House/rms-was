package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMaterialRepository implements MaterialRepository {
    private final Map<String, Material> materialMap;
    private final AtomicInteger atomicInteger;

    public InMemoryMaterialRepository() {
        materialMap = new TreeMap<>();
        atomicInteger = new AtomicInteger();
    }

    @Override
    public String save(Material material) {
        material.setId(String.format("material_%d", atomicInteger.incrementAndGet()));
        materialMap.put(material.getId(), material);
        return material.getId();
    }

    @Override
    public void update(String materialId, Material material) throws MaterialNotFoundException {
        if(!materialMap.containsKey(materialId)) {
            throw new MaterialNotFoundException();
        }
        materialMap.put(materialId, material);
    }

    @Override
    public void delete(String materialId) throws MaterialNotFoundException{
        if(!materialMap.containsKey(materialId)) {
            throw new MaterialNotFoundException();
        }
        materialMap.remove(materialId);
    }

    @Override
    public Material findById(String materialId) throws MaterialNotFoundException{
        if(!materialMap.containsKey(materialId)) {
            throw new MaterialNotFoundException();
        }
        return materialMap.get(materialId);
    }

    @Override
    public List<Material> findByIds(List<String> materialIds) {
        final List<Material> materials = new ArrayList<>();
        for(String materialId : materialIds) {
            if(materialMap.containsKey(materialId)) {
                materials.add(materialMap.get(materialId));
            }
        }
        return materials;
    }

    @Override
    public List<Material> getAll() {
        return materialMap.values().stream().toList();
    }
}
