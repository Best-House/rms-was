package com.hojun.service.domain.aggregate.recipe.infra;

import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.exception.NotFoundRecipeException;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
    Recipe findById(String recipeId) throws NotFoundRecipeException;
    Recipe save(Recipe recipe);
}
