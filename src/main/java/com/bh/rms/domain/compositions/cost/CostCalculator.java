package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.recipe.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CostCalculator {
    private final Map<String, Double> materialUnitPriceMap;

    public CostCalculator() {
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

    public List<String> getUnknownPriceOf(List<String> materials) {
        return materials.stream()
                .filter(materialId -> !materialUnitPriceMap.containsKey(materialId))
                .collect(Collectors.toList());
    }

    public double calculateCost(List<Ingredient> ingredients) {
        if (ingredients.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Ingredient ingredient : ingredients) {
                String materialId = ingredient.materialId();
                if(materialUnitPriceMap.containsKey(materialId)) {
                    final double amount = ingredient.amount();
                    final double unitPrice = materialUnitPriceMap.get(materialId);
                    final double price = unitPrice * amount;
                    result += price;
                }
            }
            return result;
        }
    }

}
