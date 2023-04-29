package com.hojun.service.domain.aggregate.material.infra;

import com.hojun.service.domain.aggregate.material.Material;

public interface MaterialRepository {
    Material save(Material material);

    Material find(String materialId);

    Material update(String materialId, Material material);

    Material delete(String materialId);
}
