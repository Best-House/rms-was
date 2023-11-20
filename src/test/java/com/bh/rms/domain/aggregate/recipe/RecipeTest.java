package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe("recipe1", "a", null);
        Recipe recipe2 = new Recipe("recipe1", "b", null);
        assertEquals(recipe1, recipe2);
    }

    @Test
    void entityIdTest() {
        new Recipe("recipe_1", "r1", null);
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Recipe(null, "r1", null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            new Recipe("", "r1", null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            new Recipe(" ", "r1", null);
        });
    }


    @Test
    void setName() {
        new Recipe("r1", null);
        assertThrows(InvalidRecipeException.class, ()->{
            new Recipe(null, null);
        });

        assertThrows(InvalidRecipeException.class, ()->{
            new Recipe("", null);
        });

        assertThrows(InvalidRecipeException.class, ()->{
            new Recipe(" ", null);
        });
    }

    @Test
    void setIngredient() {
        Recipe recipe1 = new Recipe("r1", null);
        assertNotNull(recipe1.getIngredients());
        assertTrue(recipe1.getIngredients().isEmpty());
    }


    @Test
    public void validateIngredientsAmountTest() {
        new Recipe("recipe_1", List.of(new Ingredient("material1", 1.0)));

        assertThrows(InvalidIngredientAmountException.class, ()->{
            new Recipe("recipe_1", List.of(new Ingredient("material1", -1.0)));
        });
    }

    @Test
    public void getContainedMaterialIdsTest() {
        Recipe recipe = new Recipe("recipe_1", List.of(new Ingredient("material1", 1.0)));
        assertTrue(recipe.getMaterialIdsOfIngredients().contains("material1"));
    }

    @Test
    public void getContainedMaterialIdsWithNoIngredients() {
        Recipe recipe = new Recipe("recipe_1", Collections.emptyList());
        assertTrue(recipe.getMaterialIdsOfIngredients().isEmpty());
    }

}