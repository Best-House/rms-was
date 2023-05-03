package com.hojun.service.domain.aggregate.recipe.infra;

import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.exception.NotFoundRecipeException;

public interface RecipeRepository {
    Recipe findById(String recipeId) throws NotFoundRecipeException;
    Recipe save(Recipe recipe);
}
