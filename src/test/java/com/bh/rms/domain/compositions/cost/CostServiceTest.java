package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

class CostServiceTest {
    RecipeRepository recipeRepository;
    PriceRegistryFactory priceRegistryFactory;
    CostService costService;
    Recipe recipe;
    PriceRegistry priceRegistry;

    @BeforeEach
    public void setup() {
        recipeRepository = mock(RecipeRepository.class);
        priceRegistryFactory = mock(PriceRegistryFactory.class);
        recipe = mock(Recipe.class);
        priceRegistry = mock(PriceRegistry.class);
        costService = new CostService(recipeRepository, priceRegistryFactory);
    }

    @Test
    void getCost() {
        when(recipeRepository.findById("recipe1")).thenReturn(recipe);
        when(recipe.getMaterialIdsOfIngredients()).thenReturn(Collections.emptyList());
        when(priceRegistryFactory.defaultAndRecentPurchase(anyList())).thenReturn(priceRegistry);

        costService.getRecentCost("recipe1");

        verify(recipeRepository).findById("recipe1");
        verify(recipe).getMaterialIdsOfIngredients();
        verify(priceRegistryFactory).defaultAndRecentPurchase(Collections.emptyList());
        verify(recipe).calculateCost(priceRegistry);
        verify(priceRegistry).getUnknownPriceOf(Collections.emptyList());
    }


}
