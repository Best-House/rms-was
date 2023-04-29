package com.hojun.service.infra;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.exception.NotExistMaterialException;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryMaterialRepository implements MaterialRepository {
    private final Map<String, Material> materialMap;

    public InMemoryMaterialRepository() {
        materialMap = new HashMap<>();
    }

    @Override
    public Material save(Material material) {
        material.setId(material.getName());
        materialMap.put(material.getId(), material);
        return material;
    }

    @Override
    public Material find(String materialId) {
        Material material = materialMap.get(materialId);
        if(material == null) {
            throw new NotExistMaterialException();
        }
        return material;
    }

    @Override
    public List<Material> findMaterials(List<String> materialIds) {
        final List<Material> materials = new ArrayList<>();
        for(String materialId : materialIds) {
            if(materialMap.containsKey(materialId)) {
                materials.add(materialMap.get(materialId));
            }
        }
        return materials;
    }

    @Override
    public Material update(String materialId, Material material) {
        Material foundMaterial = materialMap.get(materialId);
        if(foundMaterial == null) {
            throw new NotExistMaterialException();
        }
        materialMap.put(materialId, material);
        return material;
    }

    @Override
    public Material delete(String materialId) {
        return materialMap.remove(materialId);
    }
}
