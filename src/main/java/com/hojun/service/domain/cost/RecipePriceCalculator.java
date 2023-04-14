package com.hojun.service.domain.cost;

public class RecipePriceCalculator {

    public int calculate(Recipe recipe, MarketPrice marketPrice) {
        if (recipe.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Ingredient ingredient : recipe.getIngredients()) {
                if(marketPrice.contains(ingredient.material())) {
                    final int pricePerAmount = marketPrice.getPrice(ingredient.material());
                    final int price = pricePerAmount * ingredient.amount();
                    result += price;
                }
            }
            return result;
        }
    }
}
