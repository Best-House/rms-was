package com.hojun.service.controller;

import com.hojun.service.domain.service.RecipeCostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    private final RecipeCostService recipeCostService;

    public RecipeController(
            RecipeCostService recipeCostService
    ) {
        this.recipeCostService = recipeCostService;
    }

    @GetMapping("/recipe/{recipeId}/cost")
    public RecipeCostService.RecipeCostResult getRecipeCost(@PathVariable String recipeId) {
        return recipeCostService.getCost(recipeId);
    }
}
