package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import com.bh.rms.domain.aggregate.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CostServiceTest {
    RecipeRepository recipeRepository;
    MaterialRepository materialRepository;
    CostService costService;

    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialRepository = mock(MaterialRepository.class);
        costService = new CostService(recipeRepository, materialRepository);
    }

    @Test
    void getCost() {
//        Recipe recipe = new Recipe("recipe-1");
    }

    @Test
    public void getCostWithEmptyPriceMap() {
        final String recipeId = "recipe-1";
        final List<Material> materials = List.of(
                new Material("m1", "1", null),
                new Material("m2", "2", null)
        );
        final List<String> materialIds = materials.stream()
                .map(Material::getId)
                .collect(Collectors.toList());

        Recipe recipe = mock(Recipe.class);
        when(recipeRepository.findById(recipeId)).thenReturn(recipe);
        when(recipe.getMaterialIdsOfIngredients()).thenReturn(materialIds);
        when(materialRepository.findByIds(materialIds)).thenReturn(materials);

        CostService.CostResult result = costService.getCost(recipeId);

        verify(recipe).getCost(any(CostCalculator.class));
        assertEquals(0, result.getCost());
        assertEquals(2, result.getUnknownPriceMaterials().size());
        assertEquals("m1", result.getUnknownPriceMaterials().get(0).getId());
        assertEquals("m2", result.getUnknownPriceMaterials().get(1).getId());
    }

}