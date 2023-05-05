package com.hojun.service.controller;

import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.service.RecipeService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(
            RecipeService recipeService
    ) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipes")
    public Recipe create(@RequestBody RecipeCreateParams params) {
        return recipeService.create(params.getName(), params.getIngredients());
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
    public RecipeService.RecipeCostResult getCost(@PathVariable String recipeId) {
        return recipeService.getCost(recipeId);
    }

    @Data
    public static class RecipeCreateParams {
        private String name;
        private Map<String, Double> ingredients;
    }
}
