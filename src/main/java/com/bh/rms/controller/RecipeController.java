package com.bh.rms.controller;

import com.bh.rms.domain.compositions.cost.CostService;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeController extends AbstractApiController {
    private final RecipeService recipeService;
    private final CostService costService;

    public RecipeController(
            RecipeService recipeService,
            CostService costService
    ) {
        this.recipeService = recipeService;
        this.costService = costService;
    }

    @PostMapping("/recipes")
    public RecipeCreateResponse create(@RequestBody RecipeCreateRequest request) {
        String createdRecipeId = recipeService.create(request.getName(), request.getMaterialAmountMap());
        return new RecipeCreateResponse(createdRecipeId);
    }

    @PutMapping("/recipes/{recipeId}")
    public void update(@PathVariable String recipeId, @RequestBody RecipeCreateRequest request) {
        recipeService.update(recipeId, request.getName(), request.getMaterialAmountMap());
    }

    @DeleteMapping("/recipes/{recipeId}")
    public void deleteRecipe(@PathVariable String recipeId) {
        recipeService.delete(recipeId);
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAll();
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe getRecipe(@PathVariable String recipeId) {
        return recipeService.get(recipeId);
    }

    @GetMapping("/recipes/{recipeId}/cost")
    public CostService.CostResult getCost(@PathVariable String recipeId) {
        return costService.getCost(recipeId);
    }

    @Data
    public static class RecipeCreateRequest {
        private String name;
        private List<MaterialAmount> materialAmounts;

        private Map<String, Double> getMaterialAmountMap() {
            if(materialAmounts == null) {
                return Collections.emptyMap();
            }

            Map<String, Double> materialAmountMap = new HashMap<>();
            for(MaterialAmount materialAmount : materialAmounts) {
                materialAmountMap.put(materialAmount.getMaterialId(), materialAmount.getAmount());
            }
            return materialAmountMap;
        }
    }

    @Data
    public static class MaterialAmount {
        private String materialId;
        private double amount;
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCreateResponse {
        private String id;
    }
}
