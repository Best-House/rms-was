package com.bh.rms.domain.aggregate.recipe.service;

import com.bh.rms.domain.aggregate.material.service.MaterialService;
import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final MaterialService materialService;


    public RecipeService(
            RecipeRepository recipeRepository,
            MaterialService materialService
    ) {
        this.recipeRepository = recipeRepository;
        this.materialService = materialService;
    }

    public String create(String name, List<Ingredient> ingredients) {
        Recipe recipe = new Recipe(name, ingredients);
        if(!materialService.isAllExist(recipe.getMaterialIdsOfIngredients())) {
            throw new InvalidRecipeException();
        }
        return recipeRepository.create(recipe);
    }

    public void update(String recipeId, String name, List<Ingredient> ingredients) {
        Recipe recipe = new Recipe(recipeId, name, ingredients);
        if(!materialService.isAllExist(recipe.getMaterialIdsOfIngredients())) {
            throw new InvalidRecipeException();
        }
        recipeRepository.update(recipe);
    }

    public void delete(String recipeId) {
        recipeRepository.delete(recipeId);
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe get(String recipeId) {
        return recipeRepository.findById(recipeId);
    }
}
