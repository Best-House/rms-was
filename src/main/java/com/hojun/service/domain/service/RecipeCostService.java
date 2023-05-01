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
    private final MaterialService materialService;


    public RecipeCostService(RecipeRepository recipeRepository, MaterialService materialService) {
        this.recipeRepository = recipeRepository;
        this.materialService = materialService;
    }


    public RecipeCostResult getCost(String recipeId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        Map<String, Double> materialUnitPrice = materialService.getMaterialUnitPrice(recipe.getContainedMaterialIds());

//        List<Material> materials = materialRepository.findMaterials(recipe.getContainedMaterialIds());
//        Map<String, Double> materialUnitPrice = getMaterialUnitPrice(materials);
        return new RecipeCostResult(
                recipe.getCost(materialUnitPrice),
                recipe.getContainedMaterialIds().stream()
                        .filter(id -> !materialUnitPrice.containsKey(id))
                        .collect(Collectors.toList())
        );
//                materials.stream()
//                        .filter(material -> !material.hasPriceInfo())
//                        .collect(Collectors.toList())
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCostResult {
        private double cost;
        private List<String> unknownPriceMaterials;
    }
}
