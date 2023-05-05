package com.hojun.service.domain.aggregate.recipe.infra;

import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.exception.NotFoundRecipeException;

import java.util.List;

public interface RecipeRepository {
    Recipe save(Recipe recipe);
    void delete(String recipeId);
    List<Recipe> findAll();

    Recipe findById(String recipeId) throws NotFoundRecipeException;
}
