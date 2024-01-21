package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.material.MaterialService;
import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
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

    public void create(Recipe recipe) {
        if(!materialService.isAllExist(recipe.getMaterialIdsOfIngredients())) {
            throw new InvalidRecipeException();
        }
        recipeRepository.create(recipe);
    }

    public void update(Recipe recipe) {
        if(!materialService.isAllExist(recipe.getMaterialIdsOfIngredients())) {
            throw new InvalidRecipeException();
        }
        recipeRepository.update(recipe);
    }

    // forward only
    public void delete(String recipeId) {
        recipeRepository.delete(recipeId);
    }

    public Recipe get(String recipeId) {
        return recipeRepository.findById(recipeId);
    }
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

}
