package com.hojun.service.controller;

import com.hojun.service.domain.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(
            RecipeService recipeService
    ) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/cost")
    public RecipeService.RecipeCostResult getRecipeCost(@PathVariable String recipeId) {
        return recipeService.getCost(recipeId);
    }
}
