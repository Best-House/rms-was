package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;


    public CostService(RecipeRepository recipeRepository, MaterialRepository materialRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }

    public CostResult getCost(String recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId);
        List<String> materialIdsOfIngredients = recipe.getMaterialIdsOfIngredients();

        List<Material> materials = materialRepository.findByIds(materialIdsOfIngredients);

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        return new CostResult(
                recipe.getCost(costCalculator),
                costCalculator.getUnknownPriceOf(materialIdsOfIngredients)
        );
    }

    @AllArgsConstructor
    @Data
    public static class CostResult {
        private double cost;
        private List<String> unknownPriceMaterialIds;
    }
}
