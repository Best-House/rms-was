package com.bh.rms.domain.aggregate.material.service;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public boolean isAllExist(List<String> materialIds) {
        List<Material> materials = materialRepository.findByIds(materialIds); // material service 로 추상화하기
        return materialIds.size() == materials.size();
    }

    // forward only
    public String create(String name, Double defaultUnitPrice) {
        Material material = new Material(name, defaultUnitPrice);
        return materialRepository.create(material);
    }

    public void update(String materialId, String name, Double defaultUnitPrice) {
        Material material = new Material(materialId, name, defaultUnitPrice);
        materialRepository.update(material);
    }

    public void delete(String materialId) {
        materialRepository.delete(materialId);
    }

    public Material get(String materialId) {
        return materialRepository.findById(materialId);
    }

    public List<Material> getAll() {
        return materialRepository.findAll();
    }
}
