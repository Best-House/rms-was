package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipePriceCalculatorTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private Ingredient ingredient4;
    private Map<Ingredient, Integer> priceList;

    @BeforeEach
    void setup() {
        ingredient1 = new Ingredient();
        ingredient2 = new Ingredient();
        ingredient3 = new Ingredient();
        ingredient4 = new Ingredient();

        priceList = new HashMap<>();
        priceList.put(ingredient1, 1);
        priceList.put(ingredient2, 2);

    }

    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe recipe = new Recipe();

        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();
        RecipePrice recipePrice = recipePriceCalculator.calculatePrice(recipe, priceList);
        assertEquals(0, recipePrice.getPrice());
        assertTrue(recipePrice.getIngredientsWithoutPriceTag().isEmpty());
    }

    @Test
    void calculateRecipePriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1, 1);
        recipe.addIngredient(ingredient2, 2);
        recipe.addIngredient(ingredient3, 1);
        recipe.addIngredient(ingredient4, 2);
        RecipePriceCalculator recipePriceCalculator = new RecipePriceCalculator();

        RecipePrice recipePrice = recipePriceCalculator.calculatePrice(recipe, priceList);

        assertEquals(5, recipePrice.getPrice());
        assertTrue(recipePrice.getIngredientsWithoutPriceTag().contains(ingredient3));
        assertTrue(recipePrice.getIngredientsWithoutPriceTag().contains(ingredient4));
    }
}
