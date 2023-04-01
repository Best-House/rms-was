package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Ingredient ingredient1;

    @BeforeEach
    void setup() {
        ingredient1 = new Ingredient(new Material(), 1);
    }

    @Test
    void emptyRecipeTest() {
        Recipe recipe = new Recipe();
        assertTrue(recipe.isEmpty());
    }

    @Test
    void isNotEmptyRecipeTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        assertFalse(recipe.isEmpty());
    }

    @Test
    void countIngredientsTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(new Ingredient(new Material(), 1));
        recipe.addIngredient(new Ingredient(new Material(), 2));
        recipe.addIngredient(new Ingredient(new Material(), 3));
        assertEquals(3, recipe.getNumberOfIngredients());
    }

    @Test
    void immutabilityTest() {
        Recipe recipe = new Recipe();
        List<Ingredient> ingredientQuantities = recipe.getIngredients();
        ingredientQuantities.add(ingredient1);

        assertTrue(recipe.isEmpty());
    }
}