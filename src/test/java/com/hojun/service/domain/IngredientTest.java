package com.hojun.service.domain;

import com.hojun.service.domain.Ingredient;
import com.hojun.service.domain.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void amountTest() {
        Material material = new Material();
        Ingredient ingredient = new Ingredient(material, 1);
        assertEquals(1, ingredient.amount());
    }

    @Test
    public void materialTest() {
        Material material = new Material();
        Ingredient ingredient = new Ingredient(material, 1);
        assertEquals(material, ingredient.material());
    }
}