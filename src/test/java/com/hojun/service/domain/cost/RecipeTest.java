package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Ingredient ingredient1;

    @BeforeEach
    void setup() {
        ingredient1 = new Ingredient();
    }

    @Test
    void emptyRecipeTest() {
        Recipe recipe = new Recipe();
        assertTrue(recipe.isEmpty());
    }

    @Test
    void isNotEmptyRecipeTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1, 1);
        assertFalse(recipe.isEmpty());
    }

    @Test
    void immutabilityTest() {
        Recipe recipe = new Recipe();
        Map<Ingredient, Integer> ingredientQuantities = recipe.getIngredientQuantities();
        ingredientQuantities.put(ingredient1, 1);

        assertTrue(recipe.isEmpty());
    }
}