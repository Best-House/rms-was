package com.bh.rms.domain.aggregate.recipe.service;

import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
        String createdRecipeId = recipeService.create("name", Collections.emptyList());
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    void materialMismatchTest() {
        when(materialRepository.findByIds(anyList())).thenReturn(Collections.emptyList());

        assertThrows(MaterialNotFoundException.class, ()->{
            String createdRecipeId = recipeService.create("name", List.of(new Ingredient("m1", 1.0), new Ingredient("m2", 1.0)));
        });

        verify(materialRepository).findByIds(List.of("m1", "m2"));
    }

    @Test
    void getTest() {
        final String recipeId = "recipe-1";
        Recipe createdRecipe = recipeService.get(recipeId);
        verify(recipeRepository).findById(recipeId);
    }


}