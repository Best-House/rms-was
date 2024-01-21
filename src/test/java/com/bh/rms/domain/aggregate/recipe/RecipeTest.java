package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setId("recipe");
        recipe2.setId("recipe");
        assertEquals(recipe1, recipe2);
    }

    @Test
    void entityIdTest() {
        Recipe recipe = new Recipe();
        assertThrows(InvalidAggregateIdException.class, ()->{
            recipe.setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            recipe.setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            recipe.setId(" ");
        });
    }


    @Test
    void setName() {
        Recipe recipe = new Recipe();
        recipe.setName("recipe");

        assertThrows(InvalidRecipeException.class, ()->{
            recipe.setName(null);
        });

        assertThrows(InvalidRecipeException.class, ()->{
            recipe.setName("");
        });

        assertThrows(InvalidRecipeException.class, ()->{
            recipe.setName(" ");
        });
    }

    @Test
    void setIngredient() {
        Recipe recipe1 = new Recipe();
        assertNotNull(recipe1.getIngredients());
        assertTrue(recipe1.getIngredients().isEmpty());

        assertThrows(UnsupportedOperationException.class, () -> {
            recipe1.getIngredients().remove(0);
        });
    }


    @Test
    public void validateIngredientsAmountTest() {
        Recipe recipe = new Recipe();

        recipe.setIngredients(List.of(new Ingredient("material1", 1.0)));

        assertThrows(InvalidIngredientAmountException.class, ()->{
            recipe.setIngredients(List.of(new Ingredient("material1", -1.0)));
        });
    }

    @Test
    public void getMaterialIdsOfIngredients() {
        Recipe recipe = new Recipe();
        recipe.setIngredients(
                List.of(
                        new Ingredient("material1", 1.0),
                        new Ingredient("material2", 2.0)
                )
        );

        assertEquals(2, recipe.getMaterialIdsOfIngredients().size());
        assertTrue(recipe.getMaterialIdsOfIngredients().contains("material1"));
        assertTrue(recipe.getMaterialIdsOfIngredients().contains("material2"));
    }

    @Test
    public void getMaterialIdsOfIngredientsWithEmptyIngredients() {
        Recipe recipeWithEmptyIngredients = new Recipe();
        assertTrue(recipeWithEmptyIngredients.getMaterialIdsOfIngredients().isEmpty());
    }

}