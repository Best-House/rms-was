package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IngredientsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void invalidIngredientAmountExceptionTest() {
        assertThrows(InvalidIngredientAmountException.class, ()->{
            Ingredients ingredients = new Ingredients(Map.of("material1", -1.0));
        });
    }

    @Test
    public void getContainedMaterialIdsTest() {
        Ingredients ingredients = new Ingredients(Map.of("material1", 1.0));
        assertTrue(ingredients.getContainedMaterialIds().contains("material1"));
        assertFalse(ingredients.getContainedMaterialIds().contains("material2"));
    }


    @Test
    void getCostTest() {
        Ingredients ingredients = new Ingredients(Map.of("material1", 2.0, "material2", 3.0));
        double recipeCost = ingredients.calculateCost(Map.of("material1", 1.0, "material2", 2.0));
        assertEquals(8.0, recipeCost);
    }

    @Test
    void getCostWithEmptyIngredientTest() {
        Ingredients ingredients = new Ingredients(Collections.EMPTY_MAP);
        double recipeCost = ingredients.calculateCost(Map.of("material1", 1.0, "material2", 2.0));
        assertEquals(0.0, recipeCost);
    }

    @Test
    void getCostWithEmptyPriceMapTest() {
        Ingredients ingredients = new Ingredients(Map.of("material1", 1.0, "material3", 1.0));
        double recipeCost = ingredients.calculateCost(Collections.EMPTY_MAP);
        assertEquals(0.0, recipeCost);
    }


//    @Test
//    public void materialTest() {
//        Ingredient ingredient = new Ingredient("material1", 1);
//        assertEquals("material1", ingredient.materialId());
//    }
}