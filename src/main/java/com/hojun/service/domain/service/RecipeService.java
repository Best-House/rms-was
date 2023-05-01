package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;


    public RecipeService(RecipeRepository recipeRepository, MaterialRepository materialRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }


    public RecipeCostResult getCost(String recipeId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        List<Material> materials = materialRepository.findMaterials(recipe.getContainedMaterialIds());

        return new RecipeCostResult(
                recipe.getCost(MaterialService.getMaterialUnitPrice(materials)),
                materials.stream()
                        .filter(material -> !material.hasPriceInfo())
                        .collect(Collectors.toList())
        );
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCostResult {
        private double cost;
        private List<Material> unknownPriceMaterials;
    }
}
