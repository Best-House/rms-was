package com.hojun.service.domain.not_aggregate.material_price;

import com.hojun.service.domain.aggregate.material.Material;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaterialUnitPrice {
    private final Map<Material, Double> priceMap;

    public MaterialUnitPrice(Map<Material, Double> priceMap) {
        this.priceMap = priceMap;
    }

    public boolean contains(Material material) {
        return priceMap.containsKey(material);
    }
    public Double getPrice(Material material) {
        return priceMap.getOrDefault(material, 0.0);
    }

    public List<Material> getUnknownPriceMaterials(List<Material> materialList) {
        return materialList.stream()
                .filter(material -> !priceMap.containsKey(material))
                .collect(Collectors.toList());
    }
}
