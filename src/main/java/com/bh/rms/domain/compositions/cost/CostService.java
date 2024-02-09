package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostService {
    private final RecipeRepository recipeRepository;
    private final PriceRegistryFactory priceRegistryFactory;


    public CostService(
            RecipeRepository recipeRepository,
            PriceRegistryFactory priceRegistryFactory
    ) {
        this.recipeRepository = recipeRepository;
        this.priceRegistryFactory = priceRegistryFactory;
    }

    public CostResult getRecentCost(String recipeId) {
        final Recipe recipe = recipeRepository.findById(recipeId);
        final List<String> materialIdsOfIngredients = recipe.getMaterialIdsOfIngredients();

        final PriceRegistry priceRegistry = priceRegistryFactory.defaultAndRecentPurchase(materialIdsOfIngredients);
        return new CostResult(
                recipe.calculateCost(priceRegistry),
                priceRegistry.getUnknownPriceOf(materialIdsOfIngredients)
        );
    }

    @AllArgsConstructor
    @Data
    public static class CostResult {
        private double cost;
        private List<String> unknownPriceMaterialIds;
    }
}
