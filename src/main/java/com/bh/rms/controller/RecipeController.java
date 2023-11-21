package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.service.RecipeService;
import com.bh.rms.domain.compositions.cost.CostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String createdRecipeId = recipeService.create(request.getName(), request.getIngredients());
        return new RecipeCreateResponse(createdRecipeId);
    }

    @PutMapping("/recipes/{recipeId}")
    public void update(@PathVariable String recipeId, @RequestBody RecipeCreateRequest request) {
        recipeService.update(recipeId, request.getName(), request.getIngredients());
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
        private List<Ingredient> ingredients;
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCreateResponse {
        private String id;
    }
}
