package com.hojun.service.domain.repository;

import com.hojun.service.domain.model.Recipe;
import com.hojun.service.domain.exception.NotFoundRecipeException;

public interface RecipeRepository {
    Recipe getRecipe(String recipeId) throws NotFoundRecipeException;
}
