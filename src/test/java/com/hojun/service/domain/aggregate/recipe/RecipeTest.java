package com.hojun.service.domain.aggregate.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipeTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private List<Ingredient> ingredients;
    private Map<String, Double> materialUnitPriceMap;

    @BeforeEach
    void setup() {
        materialUnitPriceMap = Map.of("material1", 1.0, "material2", 2.0);

        ingredient1 = new Ingredient("material1", 1);
        ingredient2 = new Ingredient("material2", 2);
        ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
    }

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe("recipe1", "a", null);
        Recipe recipe2 = new Recipe("recipe1", "b", null);

        assertEquals(recipe1, recipe2);
    }

    @Test
    void calculateRecipeCostTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        double recipeCost = recipe.getCost(materialUnitPriceMap);

        assertEquals(5.0, recipeCost);
    }

    @Test
    void calculateEmptyRecipeCostTest() {
        Recipe emptyRecipe = new Recipe("", "emptyRecipe", Collections.EMPTY_LIST);

        double recipeCost = emptyRecipe.getCost(materialUnitPriceMap);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void calculateRecipeCostWithUnknownPriceTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);
        double recipeCost = recipe.getCost(Collections.EMPTY_MAP);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void getContainedMaterialsTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        assertTrue(recipe.getContainedMaterialIds().contains(ingredient1.materialId()));
        assertTrue(recipe.getContainedMaterialIds().contains(ingredient2.materialId()));
    }
}