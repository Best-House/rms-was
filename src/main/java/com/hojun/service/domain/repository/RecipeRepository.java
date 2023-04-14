package com.hojun.service.domain.repository;

import com.hojun.service.domain.Recipe;

public interface RecipeRepository {
    Recipe getRecipe(String recipeId);
}
