package com.hojun.service.domain.recipe;

import com.hojun.service.domain.material.Material;
import com.hojun.service.domain.recipe.Ingredient;
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