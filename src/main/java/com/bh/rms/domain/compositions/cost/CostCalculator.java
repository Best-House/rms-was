package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.recipe.Ingredient;

import java.util.List;
import java.util.Map;

public class CostCalculator {
    private final Map<String, Double> materialUnitPriceMap;

    public CostCalculator(Map<String, Double> materialUnitPriceMap) {
        this.materialUnitPriceMap = materialUnitPriceMap;
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
                    final double pricePerAmount = materialUnitPriceMap.get(materialId);
                    final double price = pricePerAmount * amount;
                    result += price;
                }
            }
            return result;
        }
    }
}
