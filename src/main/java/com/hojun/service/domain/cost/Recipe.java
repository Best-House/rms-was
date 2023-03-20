package com.hojun.service.domain.cost;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private Map<Ingredient, Integer> ingredientQuantities;

    public Recipe() {
        ingredientQuantities = new HashMap<>();
    }

    public int calculatePrice(Map<Ingredient, Integer> priceList) {
        if (ingredientQuantities.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Map.Entry<Ingredient, Integer> entry :ingredientQuantities.entrySet()) {
                int pricePerQuantity = priceList.get(entry.getKey());
                int price = pricePerQuantity * entry.getValue();
                result += price;
            }
            return result;
        }
    }

    public void addIngredient(int quantity, Ingredient ingredient) {
        ingredientQuantities.put(ingredient, quantity);
    }
}
