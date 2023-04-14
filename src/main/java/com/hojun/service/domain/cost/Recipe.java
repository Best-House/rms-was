package com.hojun.service.domain.cost;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Ingredient> ingredients;

    public Recipe() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public boolean isEmpty() {
        return ingredients.isEmpty();
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public int getNumberOfIngredients() {
        return ingredients.size();
    }

    public int calculate(MarketPrice marketPrice) {
        return 0;
    }
}
