package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Material material1;
    private Material material2;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    void setup() {
        material1 = new Material();
        material2 = new Material();
        ingredient1 = new Ingredient(material1, 1);
        ingredient2 = new Ingredient(material2, 1);
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
}