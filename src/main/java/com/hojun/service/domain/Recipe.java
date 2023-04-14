package com.hojun.service.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public int getNumberOfIngredients() {
        return ingredients.size();
    }

    public int getPrice(MaterialPrice materialPrice) {
        if (isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Ingredient ingredient : ingredients) {
                if(materialPrice.contains(ingredient.material())) {
                    final int pricePerAmount = materialPrice.getPrice(ingredient.material());
                    final int price = pricePerAmount * ingredient.amount();
                    result += price;
                }
            }
            return result;
        }
    }

    public List<Material> getContainedMaterials() {
        return ingredients.stream()
                .map(Ingredient::material)
                .collect(Collectors.toList());
    }
}
