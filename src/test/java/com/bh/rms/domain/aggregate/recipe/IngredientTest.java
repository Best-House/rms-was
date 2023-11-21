package com.bh.rms.domain.aggregate.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void isValidAmount() {
        Ingredient ingredientWithValidAmount = new Ingredient("material1", 1.0);
        assertTrue(ingredientWithValidAmount.isValidAmount());

        Ingredient ingredientWithInvalidAmount = new Ingredient("material1", 0.0);
        assertFalse(ingredientWithInvalidAmount.isValidAmount());
    }
}