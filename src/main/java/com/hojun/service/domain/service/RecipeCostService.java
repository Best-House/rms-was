package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeCostService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;

    public RecipeCostService(RecipeRepository recipeRepository, MaterialRepository materialRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }

    public RecipeCostResult getCost(String recipeId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        List<Material> materials = materialRepository.findMaterials(recipe.getContainedMaterialIds());
        Map<String, Double> materialUnitPrice = getMaterialUnitPrice(materials);
        return new RecipeCostResult(
                recipe.getCost(materialUnitPrice),
                materials.stream()
                        .filter(material -> !material.hasPriceInfo())
                        .collect(Collectors.toList())
        );
    }

    private Map<String, Double> getMaterialUnitPrice(List<Material> materials) {
        return materials.stream()
                .filter(Material::hasPriceInfo)
                .collect(
                        Collectors.toMap(
                                Material::getId,
                                Material::getUnitPrice
                        )
                );
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCostResult {
        private double cost;
        private List<Material> unknownPriceMaterials;
    }
}
