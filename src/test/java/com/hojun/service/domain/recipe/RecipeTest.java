package com.hojun.service.domain.recipe;

import com.hojun.service.domain.material.Material;
import com.hojun.service.domain.material_price.MaterialPrice;
import com.hojun.service.domain.recipe.Ingredient;
import com.hojun.service.domain.recipe.Recipe;
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
        materialPrice = new MaterialPrice(Map.of(material1, 1.0, material2, 2.0));
    }

    @Test
    void calculateRecipePriceTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        double recipePrice = recipe.getPrice(materialPrice);

        assertEquals(5.0, recipePrice);
    }

    @Test
    void calculateEmptyRecipePriceTest() {
        Recipe emptyRecipe = new Recipe("", "emptyRecipe", Collections.EMPTY_LIST);

        double recipePrice = emptyRecipe.getPrice(materialPrice);

        assertEquals(0.0, recipePrice);
    }

    @Test
    void calculateRecipePriceWithUnknownPriceTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);
        double recipePrice = recipe.getPrice(new MaterialPrice(Collections.EMPTY_MAP));

        assertEquals(0.0, recipePrice);
    }

    @Test
    void getContainedMaterialsTest() {
        Recipe recipe = new Recipe("", "recipe", ingredients);

        assertTrue(recipe.getContainedMaterials().contains(ingredient1.material()));
        assertTrue(recipe.getContainedMaterials().contains(ingredient2.material()));
    }
}