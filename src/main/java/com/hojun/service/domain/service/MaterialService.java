package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Map<String, Double> getMaterialUnitPrice(List<String> materialIds) {
        List<Material> materials = materialRepository.findMaterials(materialIds);
        return materials.stream()
                .filter(Material::hasPriceInfo)
                .collect(
                        Collectors.toMap(
                                Material::getId,
                                Material::getUnitPrice
                        )
                );
    }
}
