package com.bh.rms.domain.aggregate.material.infra;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotExistException;

import java.util.List;

public interface MaterialRepository {
    String save(Material material);

    void update(String materialId, Material material) throws MaterialNotExistException;

    void delete(String materialId);

    Material findById(String materialId);

    List<Material> findByIds(List<String> materialIds);

    List<Material> getAll();
}
