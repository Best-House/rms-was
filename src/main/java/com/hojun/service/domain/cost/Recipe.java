package com.hojun.service.domain.cost;

public class Recipe {
    private Ingredient ingredient;

    public int calculatePrice() {
        if (ingredient == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
