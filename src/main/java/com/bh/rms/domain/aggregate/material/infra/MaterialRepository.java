package com.bh.rms.domain.aggregate.material.infra;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;

import java.util.List;

public interface MaterialRepository {
    String create(Material material);

    void update(Material material) throws MaterialNotFoundException;

    void delete(String materialId) throws MaterialNotFoundException;

    Material findById(String materialId) throws MaterialNotFoundException;

    List<Material> findByIds(List<String> materialIds);

    List<Material> findAll();

    boolean existByIds(List<String> materialIds);
}
