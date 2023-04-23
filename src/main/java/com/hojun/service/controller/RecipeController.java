package com.hojun.service.controller;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material_price.MaterialUnitPrice;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.material_price.infra.MaterialPriceRepository;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final MaterialPriceRepository materialPriceRepository;

    public RecipeController(RecipeRepository recipeRepository, MaterialPriceRepository materialPriceRepository) {
        this.recipeRepository = recipeRepository;
        this.materialPriceRepository = materialPriceRepository;
    }


    @GetMapping("/recipe/{recipeId}/cost")
    public GetRecipeCostResponse getRecipeCost(@PathVariable String recipeId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        MaterialUnitPrice materialUnitPrice = materialPriceRepository.getCommonMaterialPrice();

        return new GetRecipeCostResponse(
                recipe.getCost(materialUnitPrice),
                materialUnitPrice.getUnknownPriceMaterials(recipe.getContainedMaterials())
        );
    }

    @AllArgsConstructor
    @Data
    public static class GetRecipeCostResponse {
        private double cost;
        private List<Material> unknownPriceMaterials;
    }
}
