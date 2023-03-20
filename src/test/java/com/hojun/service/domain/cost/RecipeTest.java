package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
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
        assertEquals(0, recipe.calculatePrice(priceList).getResult());
        assertTrue(recipe.calculatePrice(priceList).getIngredientsWithoutPriceTag().isEmpty());
    }

    @Test
    void calculateRecipePriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(1, ingredient1);
        recipe.addIngredient(2, ingredient2);
        recipe.addIngredient(1, ingredient3);
        recipe.addIngredient(2, ingredient4);

        PriceCalculation priceCalculation = recipe.calculatePrice(priceList);
        assertEquals(5, priceCalculation.getResult());
        assertTrue(priceCalculation.getIngredientsWithoutPriceTag().contains(ingredient3));
        assertTrue(priceCalculation.getIngredientsWithoutPriceTag().contains(ingredient4));
    }
}