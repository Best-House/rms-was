package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Map<Ingredient, Integer> priceList;

    @BeforeEach
    void setup() {
        ingredient1 = new Ingredient();
        ingredient2 = new Ingredient();
        priceList = new HashMap<>();
        priceList.put(ingredient1, 1);
        priceList.put(ingredient2, 2);
    }

    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe recipe = new Recipe();
        assertEquals(0, recipe.calculatePrice(priceList));
    }

    @Test
    void calculateNonEmptyRecipePriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(1, ingredient1);
        recipe.addIngredient(2, ingredient2);

        assertEquals(5, recipe.calculatePrice(priceList));
    }
}