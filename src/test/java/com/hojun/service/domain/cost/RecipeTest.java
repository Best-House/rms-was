package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Material material1;
    private Material material2;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private MaterialPrice materialPrice;

    @BeforeEach
    void setup() {
        material1 = new Material();
        material2 = new Material();
        ingredient1 = new Ingredient(material1, 1);
        ingredient2 = new Ingredient(material2, 2);
        materialPrice = new MaterialPrice();
        materialPrice.register(material1, 1);
        materialPrice.register(material2, 2);
    }

    @Test
    void calculateRecipePriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        int recipePrice = recipe.getPrice(materialPrice);

        assertEquals(5, recipePrice);
    }

    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe emptyRecipe = new Recipe();

        int recipePrice = emptyRecipe.getPrice(materialPrice);

        assertEquals(0, recipePrice);
    }

    @Test
    void calculateRecipePriceWithUnknownPriceTest() {
        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        int recipePrice = recipe.getPrice(new MaterialPrice());

        assertEquals(0, recipePrice);
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
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        assertEquals(2, recipe.getNumberOfIngredients());
    }
}