package com.bh.rms.infra.inmemory;

import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Profile("inmemory")
@Repository
public class InMemoryMaterialRepository implements MaterialRepository {
    private final Map<String, Material> materialMap;
    private final AtomicInteger atomicInteger;

    public InMemoryMaterialRepository() {
        materialMap = new TreeMap<>();
        atomicInteger = new AtomicInteger();
    }

    @Override
    public void create(Material material) {
        material.setId(String.format("material%d", atomicInteger.incrementAndGet()));
        materialMap.put(material.getId(), material);
    }

    @Override
    public void update(Material material) throws MaterialNotFoundException {
        if(!materialMap.containsKey(material.getId())) {
            throw new MaterialNotFoundException();
        }
        materialMap.put(material.getId(), material);
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
    public List<Material> findAll() {
        return materialMap.values().stream().toList();
    }
}
