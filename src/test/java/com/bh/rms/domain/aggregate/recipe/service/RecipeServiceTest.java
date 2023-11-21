package com.bh.rms.domain.aggregate.recipe.service;

import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import com.bh.rms.domain.aggregate.material.service.MaterialService;
import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceTest {
    RecipeRepository recipeRepository;
    MaterialService materialService;
    RecipeService recipeService;
    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialService = mock(MaterialService.class);
        recipeService = new RecipeService(recipeRepository, materialService);
    }

    @Test
    void createTest() {
        String createdRecipeId = recipeService.create("name", Collections.emptyList());
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    void materialMatchedTest() {
        when(materialService.isAllExist(anyList())).thenReturn(true);

        List<Ingredient> ingredients = List.of(
                new Ingredient("m1", 1.0),
                new Ingredient("m2", 1.0)
        );

        String createdRecipeId = recipeService.create("name", ingredients);

        verify(materialService).isAllExist(List.of("m1", "m2"));
    }

    @Test
    void materialMismatchTest() {
        when(materialService.isAllExist(anyList())).thenReturn(false);

        List<Ingredient> ingredients = List.of(
                new Ingredient("m1", 1.0),
                new Ingredient("m2", 1.0)
        );

        assertThrows(InvalidRecipeException.class, ()->{
            String createdRecipeId = recipeService.create("name", ingredients);
        });

        verify(materialService).isAllExist(List.of("m1", "m2"));
    }
}