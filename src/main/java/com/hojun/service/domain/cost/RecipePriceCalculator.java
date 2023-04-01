package com.hojun.service.domain.cost;

import java.util.ArrayList;
import java.util.List;

public class RecipePriceCalculator {

    public RecipePrice calculate(Recipe recipe, MarketPrice marketPrice) {
        if (recipe.isEmpty()) {
            return RecipePrice.EMPTY_RECIPE_PRICE;
        } else {
            int result = 0;
            final List<Ingredient> unknownPriceIngredients = new ArrayList<>();

            for(Ingredient ingredient : recipe.getIngredients()) {
                if(marketPrice.contains(ingredient.material())) {
                    final int pricePerAmount = marketPrice.getPrice(ingredient.material());
                    final int price = pricePerAmount * ingredient.amount();
                    result += price;
                } else {
                    unknownPriceIngredients.add(ingredient);
                }
            }
            return new RecipePrice(result, unknownPriceIngredients);
        }
    }
}
