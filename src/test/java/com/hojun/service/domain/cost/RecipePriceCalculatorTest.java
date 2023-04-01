package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipePriceCalculatorTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private MarketPrice marketPrice;

    @BeforeEach
    void setup() {
        ingredient1 = new Ingredient(1);
        ingredient2 = new Ingredient(2);

        marketPrice = new MarketPrice();
        marketPrice.register(ingredient1, 1);
        marketPrice.register(ingredient2, 2);
    }

    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe emptyRecipe = new Recipe();
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        RecipePrice recipePrice = recipePriceCalculator.calculate(emptyRecipe, marketPrice);

        assertEquals(0, recipePrice.price());
        assertTrue(recipePrice.unknownPriceIngredients().isEmpty());
    }

    @Test
    void calculateRecipePriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        RecipePrice recipePrice = recipePriceCalculator.calculate(recipe, marketPrice);

        assertEquals(5, recipePrice.price());
    }

    @Test
    void calculateRecipePriceWithUnknownPriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        RecipePrice recipePrice = recipePriceCalculator.calculate(recipe, new MarketPrice());

        assertEquals(0, recipePrice.price());
        assertTrue(recipePrice.unknownPriceIngredients().contains(ingredient1));
        assertTrue(recipePrice.unknownPriceIngredients().contains(ingredient2));
    }
}
