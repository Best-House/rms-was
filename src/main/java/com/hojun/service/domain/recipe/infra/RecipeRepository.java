package com.hojun.service.domain.recipe.infra;

import com.hojun.service.domain.recipe.Recipe;
import com.hojun.service.domain.recipe.exception.NotFoundRecipeException;

public interface RecipeRepository {
    Recipe getRecipe(String recipeId) throws NotFoundRecipeException;
}
