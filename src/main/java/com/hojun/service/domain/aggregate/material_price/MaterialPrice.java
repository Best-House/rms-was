package com.hojun.service.domain.aggregate.material_price;

import com.hojun.service.domain.aggregate.material.Material;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class MaterialPrice {
    protected abstract Map<Material, Double>  getPriceMap();
    public boolean contains(Material material) {
        return getPriceMap().containsKey(material);
    }
    public Double getPrice(Material material) {
        return getPriceMap().getOrDefault(material, 0.0);
    }

    public List<Material> getUnknownPriceMaterials(List<Material> materialList) {
        return materialList.stream()
                .filter(material -> !getPriceMap().containsKey(material))
                .collect(Collectors.toList());
    }
}
