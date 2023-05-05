package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void invalidIngredientAmountExceptionTest() {
        assertThrows(InvalidIngredientAmountException.class, ()->{
            Ingredient ingredient = new Ingredient(Map.of("material1", -1.0));
        });
    }

    @Test
    public void getContainedMaterialIdsTest() {
        Ingredient ingredient = new Ingredient(Map.of("material1", 1.0));
        assertTrue(ingredient.getContainedMaterialIds().contains("material1"));
        assertFalse(ingredient.getContainedMaterialIds().contains("material2"));
    }


    @Test
    void getCostTest() {
        Ingredient ingredient = new Ingredient(Map.of("material1", 2.0, "material2", 3.0));
        double recipeCost = ingredient.getCost(Map.of("material1", 1.0, "material2", 2.0));
        assertEquals(8.0, recipeCost);
    }

    @Test
    void getCostWithEmptyIngredientTest() {
        Ingredient ingredient = new Ingredient(Collections.EMPTY_MAP);
        double recipeCost = ingredient.getCost(Map.of("material1", 1.0, "material2", 2.0));
        assertEquals(0.0, recipeCost);
    }

    @Test
    void getCostWithEmptyPriceMapTest() {
        Ingredient ingredient = new Ingredient(Map.of("material1", 1.0, "material3", 1.0));
        double recipeCost = ingredient.getCost(Collections.EMPTY_MAP);
        assertEquals(0.0, recipeCost);
    }


//    @Test
//    public void materialTest() {
//        Ingredient ingredient = new Ingredient("material1", 1);
//        assertEquals("material1", ingredient.materialId());
//    }
}