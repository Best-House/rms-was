package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
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
    PurchaseRepository purchaseRepository;
    CostService costService;
    Recipe recipe;

    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        materialRepository = mock(MaterialRepository.class);
        purchaseRepository = mock(PurchaseRepository.class);
        recipe = mock(Recipe.class);
        costService = new CostService(recipeRepository, materialRepository, purchaseRepository);
    }

    @Test
    void getCost() {
        when(recipeRepository.findById("recipe1")).thenReturn(recipe);
        when(recipe.getMaterialIdsOfIngredients()).thenReturn(Collections.emptyList());
        when(materialRepository.findByIds(anyList())).thenReturn(Collections.emptyList());
        when(purchaseRepository.findRecentByMaterialIds(anyList())).thenReturn(Collections.emptyList());

        costService.getCost("recipe1");

        verify(recipe).getCost(any(CostCalculator.class));
        verify(recipe).getMaterialIdsOfIngredients();
        verify(materialRepository).findByIds(anyList());
        verify(purchaseRepository).findRecentByMaterialIds(anyList());
    }


}
