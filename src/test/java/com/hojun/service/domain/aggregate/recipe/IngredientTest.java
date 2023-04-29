package com.hojun.service.domain.aggregate.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void amountTest() {
        Ingredient ingredient = new Ingredient("material1", 1);
        assertEquals(1, ingredient.amount());
    }

    @Test
    public void materialTest() {
        Ingredient ingredient = new Ingredient("material1", 1);
        assertEquals("material1", ingredient.materialId());
    }
}