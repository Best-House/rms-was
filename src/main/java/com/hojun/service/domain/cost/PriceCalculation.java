package com.hojun.service.domain.cost;

import java.util.HashSet;
import java.util.Set;

public class PriceCalculation {
    private int result;

    private Set<Ingredient> ingredientsWithoutPriceTag;

    public PriceCalculation() {
        ingredientsWithoutPriceTag = new HashSet<>();
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setIngredientWithoutPriceTag(Ingredient ingredientWithoutPriceTag) {
        ingredientsWithoutPriceTag.add(ingredientWithoutPriceTag);
    }
    public Set<Ingredient> getIngredientsWithoutPriceTag() {
        return ingredientsWithoutPriceTag;
    }
}
