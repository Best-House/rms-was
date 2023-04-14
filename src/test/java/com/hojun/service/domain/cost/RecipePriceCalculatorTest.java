package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipePriceCalculatorTest {
    private Material material1;
    private Material material2;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private MarketPrice marketPrice;

    @BeforeEach
    void setup() {
        material1 = new Material();
        material2 = new Material();
        ingredient1 = new Ingredient(material1, 1);
        ingredient2 = new Ingredient(material2, 2);

        marketPrice = new MarketPrice();
        marketPrice.register(material1, 1);
        marketPrice.register(material2, 2);
    }

    @Test
    void calculateRecipePriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        int recipePrice = recipePriceCalculator.calculate(recipe, marketPrice);

        assertEquals(5, recipePrice);
    }

    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe emptyRecipe = new Recipe();
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        int recipePrice = recipePriceCalculator.calculate(emptyRecipe, marketPrice);

        assertEquals(0, recipePrice);
    }

    @Test
    void calculateRecipePriceWithUnknownPriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        int recipePrice = recipePriceCalculator.calculate(recipe, new MarketPrice());

        assertEquals(0, recipePrice);
    }
}
