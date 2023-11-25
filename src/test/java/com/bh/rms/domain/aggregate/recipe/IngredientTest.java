package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IngredientTest {

    @Test
    void create() {
        Ingredient ingredientWithValidAmount = new Ingredient("material1", 1.0);
    }

    @Test
    void createWithInvalidAmount() {
        assertThrows(InvalidIngredientAmountException.class, ()->{
            Ingredient ingredientWithInvalidAmount = new Ingredient("material1", 0.0);
        });
    }
}