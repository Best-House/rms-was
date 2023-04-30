package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeCostServiceTest {
    RecipeRepository recipeRepository;
    MaterialRepository materialRepository;
    RecipeCostService recipeCostService;

    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialRepository = mock(MaterialRepository.class);
        recipeCostService = new RecipeCostService(recipeRepository, materialRepository);
    }

    @Test
    public void getCostTest() {
        final String recipeId = "recipeId";

        Recipe recipe = mock(Recipe.class);
        when(recipeRepository.getRecipe(recipeId)).thenReturn(recipe);

        RecipeCostService.RecipeCostResult costResult = recipeCostService.getCost(recipeId);
    }
}