package com.hojun.service.domain.cost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipePriceCalculator {

    public RecipePrice calculatePrice(Recipe recipe, MarketPrice marketPrice) {
        if (recipe.isEmpty()) {
            return RecipePrice.ZERO_PRICE;
        } else {
            int result = 0;
            final List<Ingredient> unknownPriceIngredients = new ArrayList<>();

            for(Ingredient ingredient : recipe.getIngredients()) {
                if(marketPrice.contains(ingredient)) {
                    final int pricePerQuantity = marketPrice.getPrice(ingredient);
                    final int price = pricePerQuantity * ingredient.getAmount();
                    result += price;
                } else {
                    unknownPriceIngredients.add(ingredient);
                }
            }
            return new RecipePrice(result, unknownPriceIngredients);
        }
    }
}
