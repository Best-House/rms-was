package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;

import java.util.List;

public interface MaterialRepository {
    void create(Material material);

    void update(Material material) throws MaterialNotFoundException;

    void delete(String materialId) throws MaterialNotFoundException;

    Material findById(String materialId) throws MaterialNotFoundException;

    List<Material> findByIds(List<String> materialIds);

    List<Material> findAll();
}
