package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompoundPriceRegistry implements PriceRegistry {

    private final Map<String, Double> materialUnitPriceMap;

    public CompoundPriceRegistry() {
        this.materialUnitPriceMap = new HashMap<>();
    }

    public void putDefaultUnitPriceOf(List<Material> materials) {
        for(Material material : materials) {
            if(material.hasDefaultUnitPrice()) {
                materialUnitPriceMap.put(material.getId(), material.getDefaultUnitPrice());
            }
        }
    }

    public void putPurchaseUnitPrice(List<PurchaseItem> purchases) {
        for(PurchaseItem purchase : purchases) {
            materialUnitPriceMap.put(purchase.materialId(), purchase.getUnitPrice());
        }
    }

    @Override
    public List<String> getUnknownPriceOf(List<String> materials) {
        return materials.stream()
                .filter(materialId -> !materialUnitPriceMap.containsKey(materialId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsKey(String materialId) {
        return materialUnitPriceMap.containsKey(materialId);
    }

    @Override
    public double getUnitPrice(String materialId) {
        return materialUnitPriceMap.get(materialId);
    }
}
