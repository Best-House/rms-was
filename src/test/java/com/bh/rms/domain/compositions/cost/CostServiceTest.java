package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

class CostServiceTest {
    RecipeRepository recipeRepository;
    MaterialRepository materialRepository;
    CostService costService;
    Recipe recipe;

    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialRepository = mock(MaterialRepository.class);
        recipe = mock(Recipe.class);
        costService = new CostService(recipeRepository, materialRepository);
    }

    @Test
    void getCost() {
        when(recipeRepository.findById("recipe1")).thenReturn(recipe);
        when(recipe.getMaterialIdsOfIngredients()).thenReturn(Collections.emptyList());
        when(materialRepository.findByIds(anyList())).thenReturn(Collections.emptyList());

        costService.getCost("recipe1");

        verify(recipe).getCost(any(CostCalculator.class));
        verify(recipe).getMaterialIdsOfIngredients();
    }

    // 중복된 integration 테스트.
    // forward 되는 기능은 테스트 하지않아서 중복 테스트를 줄인다.
//    @Test
//    public void getCostWithEmptyPriceMap() {
//        final String recipeId = "recipe-1";
//        final List<Material> materials = List.of(
//                new Material("m1", "1", null),
//                new Material("m2", "2", null)
//        );
//        final List<String> materialIds = materials.stream()
//                .map(Material::getId)
//                .collect(Collectors.toList());
//
//        Recipe recipe = mock(Recipe.class);
//        when(recipeRepository.findById(recipeId)).thenReturn(recipe);
//        when(recipe.getMaterialIdsOfIngredients()).thenReturn(materialIds);
//        when(materialRepository.findByIds(materialIds)).thenReturn(materials);
//
//        CostService.CostResult result = costService.getCost(recipeId);
//
//        verify(recipe).getCost(any(CostCalculator.class));
//        assertEquals(0, result.getCost());
//        assertEquals(2, result.getUnknownPriceMaterialIds().size());
//        assertEquals("m1", result.getUnknownPriceMaterialIds().get(0));
//        assertEquals("m2", result.getUnknownPriceMaterialIds().get(1));
//    }
}