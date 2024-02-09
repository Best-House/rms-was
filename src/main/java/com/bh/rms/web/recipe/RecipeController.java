package com.bh.rms.web.recipe;

import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.RecipeService;
import com.bh.rms.domain.compositions.cost.CostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RecipeController{
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
        Recipe recipe = request.makeRecipeForCreate();
        recipeService.create(recipe);
        return new RecipeCreateResponse(recipe.getId());
    }

    @PutMapping("/recipes/{recipeId}")
    public void update(@PathVariable String recipeId, @RequestBody RecipeCreateRequest request) {
        Recipe recipe = request.makeRecipeForUpdate(recipeId);
        recipeService.update(recipe);
    }

    @DeleteMapping("/recipes/{recipeId}")
    public void delete(@PathVariable String recipeId) {
        recipeService.delete(recipeId);
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe get(@PathVariable String recipeId) {
        return recipeService.get(recipeId);
    }

    @GetMapping("/recipes")
    public List<Recipe> getAll() {
        return recipeService.getAll();
    }

    @GetMapping("/recipes/{recipeId}/cost")
    public CostService.CostResult getCost(@PathVariable String recipeId) {
        return costService.getCompoundedCost(recipeId);
    }

}
