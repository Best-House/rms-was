package com.hojun.service.domain.vo.ingredient;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.vo.ingredient.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void amountTest() {
        Material material = new Material("material1", "material1");
        Ingredient ingredient = new Ingredient(material, 1);
        assertEquals(1, ingredient.amount());
    }

    @Test
    public void materialTest() {
        Material material = new Material("material1", "material1");
        Ingredient ingredient = new Ingredient(material, 1);
        assertEquals(new Material("material1", ""), ingredient.material());
    }
}