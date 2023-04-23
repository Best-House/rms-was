package com.hojun.service.domain.aggregate.recipe.infra;

import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.exception.NotFoundRecipeException;

public interface RecipeRepository {
    Recipe getRecipe(String recipeId) throws NotFoundRecipeException;
}
