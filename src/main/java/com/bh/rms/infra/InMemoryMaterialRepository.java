package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotExistException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMaterialRepository implements MaterialRepository {
    private final Map<String, Material> materialMap;
    private final AtomicInteger atomicInteger;

    public InMemoryMaterialRepository() {
        materialMap = new HashMap<>();
        atomicInteger = new AtomicInteger();
    }

    @Override
    public Material save(Material material) {
        material.setId(String.format("material-%d", atomicInteger.incrementAndGet()));
        materialMap.put(material.getId(), material);
        return material;
    }

    @Override
    public Material findById(String materialId) {
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
    public Material update(String materialId, Material material) throws MaterialNotExistException {
        Material foundMaterial = materialMap.get(materialId);
        if(foundMaterial == null) {
            throw new MaterialNotExistException();
        }
        materialMap.put(materialId, material);
        return material;
    }

    @Override
    public Material delete(String materialId) {
        return materialMap.remove(materialId);
    }

    @Override
    public List<Material> getAll() {
        return materialMap.values().stream().toList();
    }
}
