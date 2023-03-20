package com.hojun.service.domain.cost;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private Map<Ingredient, Integer> ingredientQuantities;

    public Recipe() {
        ingredientQuantities = new HashMap<>();
    }

    public PriceCalculation calculatePrice(Map<Ingredient, Integer> priceList) {
        if (ingredientQuantities.isEmpty()) {
            return new PriceCalculation();
        } else {
            PriceCalculation priceCalculation = new PriceCalculation();

            int result = 0;
            for(Map.Entry<Ingredient, Integer> entry :ingredientQuantities.entrySet()) {
                if(priceList.containsKey(entry.getKey())) {
                    int pricePerQuantity = priceList.getOrDefault(entry.getKey(), 0);
                    int price = pricePerQuantity * entry.getValue();
                    result += price;
                } else {
                    priceCalculation.setIngredientWithoutPriceTag(entry.getKey());
                }
            }

            priceCalculation.setResult(result);
            return priceCalculation;
        }
    }

    public void addIngredient(int quantity, Ingredient ingredient) {
        ingredientQuantities.put(ingredient, quantity);
    }
}
