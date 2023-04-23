package com.hojun.service.domain.aggregate.recipe;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material_price.FixedMaterialPrice;
import com.hojun.service.domain.aggregate.material_price.MaterialPrice;
import com.hojun.service.domain.aggregate.recipe.Ingredient;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Material material1;
    private Material material2;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private List<Ingredient> ingredients;
    private MaterialPrice materialPrice;

    @BeforeEach
    void setup() {
        material1 = new Material("material1", "material1");
        material2 = new Material("material2", "material2");
        ingredient1 = new Ingredient(material1, 1);
        ingredient2 = new Ingredient(material2, 2);
        ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        materialPrice = new FixedMaterialPrice(Map.of(material1, 1.0, material2, 2.0));
    }

    @Test
    void calculateRecipeCostTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        double recipeCost = recipe.getCost(materialPrice);

        assertEquals(5.0, recipeCost);
    }

    @Test
    void calculateEmptyRecipeCostTest() {
        Recipe emptyRecipe = new Recipe("", "emptyRecipe", Collections.EMPTY_LIST);

        double recipeCost = emptyRecipe.getCost(materialPrice);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void calculateRecipeCostWithUnknownPriceTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);
        double recipeCost = recipe.getCost(new FixedMaterialPrice(Collections.EMPTY_MAP));

        assertEquals(0.0, recipeCost);
    }

    @Test
    void getContainedMaterialsTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        assertTrue(recipe.getContainedMaterials().contains(ingredient1.material()));
        assertTrue(recipe.getContainedMaterials().contains(ingredient2.material()));
    }
}