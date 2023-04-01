package com.hojun.service.domain.cost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void amountTest() {
        Ingredient ingredient = new Ingredient(1);
        assertEquals(1, ingredient.amount());
    }
}