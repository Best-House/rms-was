package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceTest {
    RecipeRepository recipeRepository;
    MaterialRepository materialRepository;
    RecipeService recipeService;
    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialRepository = mock(MaterialRepository.class);
        recipeService = new RecipeService(recipeRepository, materialRepository);
    }

    @Test
    void createTest() {
        Recipe createdRecipe = recipeService.create("name", Collections.EMPTY_MAP);
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    void getTest() {
        final String recipeId = "recipe-1";
        Recipe createdRecipe = recipeService.get(recipeId);
        verify(recipeRepository).findById(recipeId);
    }

    @Test
    public void getMaterialUnitPriceMapTest() {
        final String recipeId = "recipe-1";
        final List<Material> materials = List.of(new Material("").setId("m1"), new Material("").setId("m2"));
        final List<String> materialIds = materials.stream()
                .map(Material::getId)
                .collect(Collectors.toList());
        materials.get(0).setPriceInfo(1, 1);
        materials.get(1).setPriceInfo(4, 2);

        Recipe recipe = mock(Recipe.class);
        when(recipeRepository.findById(recipeId)).thenReturn(recipe);
        when(recipe.getContainedMaterialIds()).thenReturn(materialIds);
        when(materialRepository.findByIds(materialIds)).thenReturn(materials);

        RecipeService.RecipeCostResult result = recipeService.getCost(recipeId);

        verify(recipe).getCost(Map.of("m1", 1.0, "m2", 2.0));
        assertTrue(result.getUnknownPriceMaterials().isEmpty());
    }

    @Test
    public void getUnknownPriceMaterialsTest() {

        final String recipeId = "recipe-1";
        final List<Material> materials = List.of(new Material("").setId("m1"), new Material("").setId("m2"));
        final List<String> materialIds = materials.stream()
                .map(Material::getId)
                .collect(Collectors.toList());

        Recipe recipe = mock(Recipe.class);
        when(recipeRepository.findById(recipeId)).thenReturn(recipe);
        when(recipe.getContainedMaterialIds()).thenReturn(materialIds);
        when(materialRepository.findByIds(materialIds)).thenReturn(materials);

        RecipeService.RecipeCostResult result = recipeService.getCost(recipeId);

        verify(recipe).getCost(Collections.EMPTY_MAP);
        assertEquals(materials.get(0), result.getUnknownPriceMaterials().get(0));
        assertEquals(materials.get(1), result.getUnknownPriceMaterials().get(1));
    }
}