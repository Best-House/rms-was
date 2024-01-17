package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.material.MaterialService;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.RecipeService;
import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
import com.bh.rms.domain.aggregate.recipe.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceTest {
    // 호출부만 있는 단순 forwarding 메서드는 테스트 하지않는다.
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
    void create() {
        when(materialService.isAllExist(anyList())).thenReturn(true);

        String createdRecipeId = recipeService.create("name", Collections.emptyList());

        verify(materialService).isAllExist(anyList());
        verify(recipeRepository).create(any(Recipe.class));
    }

    @Test
    void createWithNotExistMaterial() {
        when(materialService.isAllExist(anyList())).thenReturn(false);

        assertThrows(InvalidRecipeException.class, ()->{
            recipeService.create("name", Collections.emptyList());
        });

        verify(materialService).isAllExist(anyList());
    }

    @Test
    void update() {
        when(materialService.isAllExist(anyList())).thenReturn(true);

        recipeService.update("material1", "name", Collections.emptyList());

        verify(materialService).isAllExist(anyList());
        verify(recipeRepository).update(any(Recipe.class));
    }

    @Test
    void updateWithNotExistMaterial() {
        when(materialService.isAllExist(anyList())).thenReturn(false);

        assertThrows(InvalidRecipeException.class, ()->{
            recipeService.update("material1", "name", Collections.emptyList());
        });

        verify(materialService).isAllExist(anyList());
    }
}