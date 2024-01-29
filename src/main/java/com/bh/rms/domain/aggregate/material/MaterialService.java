package com.bh.rms.domain.aggregate.material;

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
    public void create(Material material) {
        materialRepository.create(material);
    }

    public void update(Material material) {
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

    public boolean existByIds(List<String> materialIds) {
        return materialRepository.existByIds(materialIds);
    }
}
