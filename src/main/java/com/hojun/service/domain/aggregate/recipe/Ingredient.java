package com.hojun.service.domain.aggregate.recipe;

import com.hojun.service.domain.aggregate.recipe.exception.InvalidIngredientAmountException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Ingredient {
    private final Map<String, Double> materialIdAmountMap;

    public Ingredient(Map<String, Double> materialIdAmountMap) {
        if(materialIdAmountMap == null) {
            materialIdAmountMap = Collections.EMPTY_MAP;
        }
        for(Double amount : materialIdAmountMap.values()) {
            if(amount == null || amount <= 0.0) {
                throw  new InvalidIngredientAmountException();
            }
        }
        this.materialIdAmountMap = Collections.unmodifiableMap(materialIdAmountMap);;
    }

    public List<String> getContainedMaterialIds() {
        return new ArrayList<>(materialIdAmountMap.keySet());
    }

    public double getCost(Map<String, Double> materialUnitPriceMap) {
        if (materialIdAmountMap.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Map.Entry<String, Double> entity : materialIdAmountMap.entrySet()) {
                String materialId = entity.getKey();
                if(materialUnitPriceMap.containsKey(materialId)) {
                    final double amount = entity.getValue();
                    final double pricePerAmount = materialUnitPriceMap.get(materialId);
                    final double price = pricePerAmount * amount;
                    result += price;
                }
            }
            return result;
        }
    }
}
