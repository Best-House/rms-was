package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeServiceTest {
    RecipeRepository recipeRepository;
    MaterialRepository materialRepository;
    RecipeService recipeService;

    final String recipeId = "recipeId";
    Recipe recipe;

    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialRepository = mock(MaterialRepository.class);
        recipeService = new RecipeService(recipeRepository, materialRepository);

        recipe = mock(Recipe.class);
        when(recipeRepository.getRecipe(recipeId)).thenReturn(recipe);
    }

//    @Test
//    public void getCostTest() {
//        when(materialService.getMaterialUnitPrice(any())).thenReturn()
//        RecipeCostService.RecipeCostResult costResult = recipeCostService.getCost(recipeId);
//    }
}