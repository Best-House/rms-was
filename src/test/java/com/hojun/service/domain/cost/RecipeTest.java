package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe recipe = new Recipe();
        int price = recipe.calculatePrice();
        assertEquals(0, price);
    }

    @Test
    void calculateNonEmptyRecipePriceTest() {
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        recipe.addIngredient(ingredient);

        int price = recipe.calculatePrice();
        assertNotEquals(0, price);
    }
}