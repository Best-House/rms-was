package com.hojun.service.domain.cost;

import java.util.HashSet;
import java.util.Set;

public class RecipePrice {
    private int price;

    private Set<Ingredient> ingredientsWithoutPriceTag;

    public RecipePrice() {
        price = 0;
        ingredientsWithoutPriceTag = new HashSet<>();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setIngredientWithoutPriceTag(Ingredient ingredientWithoutPriceTag) {
        ingredientsWithoutPriceTag.add(ingredientWithoutPriceTag);
    }
    public Set<Ingredient> getIngredientsWithoutPriceTag() {
        return ingredientsWithoutPriceTag;
    }
}
