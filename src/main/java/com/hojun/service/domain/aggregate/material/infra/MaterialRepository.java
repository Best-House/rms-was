package com.hojun.service.domain.aggregate.material.infra;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.exception.MaterialNotExistException;

import java.util.List;

public interface MaterialRepository {
    Material save(Material material);

    Material findById(String materialId);

    List<Material> findByIds(List<String> materialIds);

    Material update(String materialId, Material material) throws MaterialNotExistException;

    Material delete(String materialId);
}
