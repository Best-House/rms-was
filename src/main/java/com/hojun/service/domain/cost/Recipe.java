package com.hojun.service.domain.cost;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private Map<Ingredient, Integer> ingredientQuantities;

    public Recipe() {
        ingredientQuantities = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        ingredientQuantities.put(ingredient, quantity);
    }

    public boolean isEmpty() {
        return ingredientQuantities.isEmpty();
    }

    public Map<Ingredient, Integer> getIngredientQuantities() {
        return new HashMap<>(ingredientQuantities);
    }
}
