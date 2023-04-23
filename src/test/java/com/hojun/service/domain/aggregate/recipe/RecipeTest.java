package com.hojun.service.domain.aggregate.recipe;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material_price.MaterialUnitPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipeTest {
    private Material material1;
    private Material material2;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private List<Ingredient> ingredients;
    private MaterialUnitPrice materialUnitPrice;

    @BeforeEach
    void setup() {
        material1 = new Material("material1", "material1");
        material2 = new Material("material2", "material2");
        ingredient1 = new Ingredient(material1, 1);
        ingredient2 = new Ingredient(material2, 2);
        ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        materialUnitPrice = new MaterialUnitPrice(Map.of(material1, 1.0, material2, 2.0));
    }

    @Test
    void calculateRecipeCostTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        double recipeCost = recipe.getCost(materialUnitPrice);

        assertEquals(5.0, recipeCost);
    }

    @Test
    void calculateEmptyRecipeCostTest() {
        Recipe emptyRecipe = new Recipe("", "emptyRecipe", Collections.EMPTY_LIST);

        double recipeCost = emptyRecipe.getCost(materialUnitPrice);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void calculateRecipeCostWithUnknownPriceTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);
        double recipeCost = recipe.getCost(new MaterialUnitPrice(Collections.EMPTY_MAP));

        assertEquals(0.0, recipeCost);
    }

    @Test
    void getContainedMaterialsTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        assertTrue(recipe.getContainedMaterials().contains(ingredient1.material()));
        assertTrue(recipe.getContainedMaterials().contains(ingredient2.material()));
    }
}