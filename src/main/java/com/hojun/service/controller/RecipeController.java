package com.hojun.service.controller;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import com.hojun.service.domain.aggregate.user_material_price.UserMaterialPrice;
import com.hojun.service.domain.record.MaterialUnitPrice;
import com.hojun.service.domain.aggregate.user_material_price.infra.UserMaterialPriceRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final UserMaterialPriceRepository userMaterialPriceRepository;

    public RecipeController(RecipeRepository recipeRepository, UserMaterialPriceRepository userMaterialPriceRepository) {
        this.recipeRepository = recipeRepository;
        this.userMaterialPriceRepository = userMaterialPriceRepository;
    }


    @GetMapping("/recipe/{recipeId}/cost")
    public GetRecipeCostResponse getRecipeCost(@PathVariable String recipeId, String userMaterialPriceId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        UserMaterialPrice userMaterialPrice = userMaterialPriceRepository.getUserMaterialPrice(userMaterialPriceId);
        MaterialUnitPrice materialUnitPrice = userMaterialPrice.getMaterialUnitPrice();

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
