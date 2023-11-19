package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe("a", Collections.emptyList()).setId("recipe1");
        Recipe recipe2 = new Recipe("b", Collections.emptyList()).setId("recipe1");
        assertEquals(recipe1, recipe2);
    }

    @Test
    void entityIdTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", Collections.emptyList()).setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", Collections.emptyList()).setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", Collections.emptyList()).setId(" ");
        });
    }



    @Test
    public void getContainedMaterialIdsTest() {
        Recipe recipe = new Recipe("recipe1", List.of(new Ingredient("material1", 1.0)));
        assertTrue(recipe.getMaterialIdsOfIngredients().contains("material1"));
    }

    @Test
    public void getContainedMaterialIdsWithNoIngredients() {
        Recipe recipe = new Recipe("recipe1", Collections.emptyList());
        assertTrue(recipe.getMaterialIdsOfIngredients().isEmpty());
    }
}