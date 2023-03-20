package com.hojun.service.domain.cost;

import java.util.Map;

public class RecipePriceCalculator {

    public PriceCalculation calculatePrice(Recipe recipe, Map<Ingredient, Integer> priceList) {
        if (recipe.isEmpty()) {
            return new PriceCalculation();
        } else {
            PriceCalculation priceCalculation = new PriceCalculation();

            int result = 0;
            for(Map.Entry<Ingredient, Integer> entry : recipe.getIngredientQuantities().entrySet()) {
                if(priceList.containsKey(entry.getKey())) {
                    int pricePerQuantity = priceList.getOrDefault(entry.getKey(), 0);
                    int price = pricePerQuantity * entry.getValue();
                    result += price;
                } else {
                    priceCalculation.setIngredientWithoutPriceTag(entry.getKey());
                }
            }

            priceCalculation.setResult(result);
            return priceCalculation;
        }
    }
}
