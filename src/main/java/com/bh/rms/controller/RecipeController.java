package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.service.RecipeService;
import com.bh.rms.domain.compositions.cost.CostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public RecipeCreateResponse create(@Valid @RequestBody RecipeCreateRequest request) {
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
        @NotBlank
        private String name;
        @Valid
        private List<IngredientInput> ingredients;

        public List<Ingredient> getIngredients() {
            if(ingredients == null) {
                return Collections.emptyList();
            }
            return ingredients.stream()
                    .map(ingredientInput -> new Ingredient(
                            ingredientInput.getMaterialId(),
                            ingredientInput.getAmount()
                            )
                    ).collect(Collectors.toList());
        }
    }

    @Data
    public static class IngredientInput {
        @NotBlank
        private String materialId;
        @Positive
        private Double amount;
    }

    @AllArgsConstructor
    @Data
    public static class RecipeCreateResponse {
        private String id;
    }
}
