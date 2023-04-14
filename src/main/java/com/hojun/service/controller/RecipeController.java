package com.hojun.service.controller;

import com.hojun.service.domain.Material;
import com.hojun.service.domain.MaterialPrice;
import com.hojun.service.domain.Recipe;
import com.hojun.service.domain.repository.MaterialPriceRepository;
import com.hojun.service.domain.repository.RecipeRepository;
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

    public RecipeController() {
        recipeRepository = recipeId -> new Recipe();
        materialPriceRepository = MaterialPrice::new;
    }


    @GetMapping("/recipe/{recipeId}/price")
    public GetRecipePriceResponse getRecipePrice(@PathVariable String recipeId) {
        Recipe recipe = recipeRepository.getRecipe(recipeId);
        MaterialPrice materialPrice = materialPriceRepository.getCommonMaterialPrice();

        return new GetRecipePriceResponse(
                recipe.getPrice(materialPrice),
                materialPrice.getUnknownPriceMaterials(recipe.getContainedMaterials())
        );
    }

    @AllArgsConstructor
    @Data
    public static class GetRecipePriceResponse {
        private int price;
        private List<Material> unknownPriceMaterials;
    }
}
