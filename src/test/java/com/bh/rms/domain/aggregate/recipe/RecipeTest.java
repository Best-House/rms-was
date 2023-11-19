package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RecipeTest {

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe("a", Collections.EMPTY_MAP).setId("recipe1");
        Recipe recipe2 = new Recipe("b", Collections.EMPTY_MAP).setId("recipe1");
        assertEquals(recipe1, recipe2);
    }

    @Test
    void entityIdTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", Collections.EMPTY_MAP).setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", Collections.EMPTY_MAP).setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", Collections.EMPTY_MAP).setId(" ");
        });
    }

    @Test
    public void invalidIngredientAmountExceptionTest() {
        assertThrows(InvalidIngredientAmountException.class, ()->{
            Recipe recipe = new Recipe("recipe1", Map.of("material1", -1.0));
        });
    }

    @Test
    public void getContainedMaterialIdsTest() {
        Recipe recipe = new Recipe("recipe1", Map.of("material1", 1.0));
        assertTrue(recipe.getMaterialIdsOfIngredients().contains("material1"));
    }
}