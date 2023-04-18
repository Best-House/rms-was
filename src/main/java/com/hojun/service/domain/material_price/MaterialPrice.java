package com.hojun.service.domain.material_price;

import com.hojun.service.domain.material.Material;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ToString
public class MaterialPrice {
    private final Map<Material, Double> priceMap;

    public MaterialPrice(Map<Material, Double> priceMap) {
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
