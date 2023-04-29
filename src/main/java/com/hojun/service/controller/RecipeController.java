package com.hojun.service.controller;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;

    public RecipeController(
            RecipeRepository recipeRepository,
            MaterialRepository materialRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }

    @GetMapping("/recipe/{recipeId}/cost")
    public GetRecipeCostResponse getRecipeCost(@PathVariable String recipeId, String userMaterialPriceId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        List<Material> materials = materialRepository.findMaterials(recipe.getContainedMaterialIds());
        Map<String, Double> materialUnitPriceMap = materials.stream()
                .filter(Material::hasPriceInfo)
                .collect(
                        Collectors.toMap(
                                Material::getId,
                                Material::getUnitPrice
                        )
                );

        return new GetRecipeCostResponse(
                recipe.getCost(materialUnitPriceMap),
                materials.stream()
                        .filter(material -> !material.hasPriceInfo())
                        .collect(Collectors.toList())
        );
    }

    @AllArgsConstructor
    @Data
    public static class GetRecipeCostResponse {
        private double cost;
        private List<Material> unknownPriceMaterialIds;
    }
}
