package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.recipe.Ingredient;

import java.util.List;

public class CostCalculator {
    public static double calculateCost(List<Ingredient> ingredients, PriceRegistry priceRegistry) {
        if (ingredients.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Ingredient ingredient : ingredients) {
                String materialId = ingredient.materialId();
                if(priceRegistry.containsKey(materialId)) {
                    final double amount = ingredient.amount();
                    final double unitPrice = priceRegistry.getUnitPrice(materialId);
                    final double price = unitPrice * amount;
                    result += price;
                }
            }
            return result;
        }
    }
}
