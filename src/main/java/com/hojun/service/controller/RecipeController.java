package com.hojun.service.controller;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import com.hojun.service.domain.record.MaterialUnitPrice;
import com.hojun.service.domain.aggregate.user_material_price.infra.MaterialUnitPriceRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final MaterialUnitPriceRepository materialUnitPriceRepository;

    public RecipeController(RecipeRepository recipeRepository, MaterialUnitPriceRepository materialUnitPriceRepository) {
        this.recipeRepository = recipeRepository;
        this.materialUnitPriceRepository = materialUnitPriceRepository;
    }


    @GetMapping("/recipe/{recipeId}/cost")
    public GetRecipeCostResponse getRecipeCost(@PathVariable String recipeId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        MaterialUnitPrice materialUnitPrice = materialUnitPriceRepository.getCommonMaterialUnitPrice();

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
