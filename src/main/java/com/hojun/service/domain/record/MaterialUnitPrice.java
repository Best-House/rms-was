package com.hojun.service.domain.record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaterialUnitPrice {
    private final Map<String, Double> priceMap;

    public MaterialUnitPrice(Map<String, Double> priceMap) {
        this.priceMap = priceMap;
    }

    public boolean contains(String materialId) {
        return priceMap.containsKey(materialId);
    }
    public Double getPrice(String materialId) {
        return priceMap.getOrDefault(materialId, 0.0);
    }

    public List<String> getUnknownPriceMaterialIds(List<String> materialList) {
        return materialList.stream()
                .filter(materialId -> !priceMap.containsKey(materialId))
                .collect(Collectors.toList());
    }
}
