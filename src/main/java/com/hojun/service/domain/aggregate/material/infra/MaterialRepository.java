package com.hojun.service.domain.aggregate.material.infra;

import com.hojun.service.domain.aggregate.material.Material;

import java.util.List;

public interface MaterialRepository {
    Material save(Material material);

    Material find(String materialId);

    List<Material> findMaterials(List<String> materialIds);

    Material update(String materialId, Material material);

    Material delete(String materialId);
}
