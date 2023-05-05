package com.bh.rms.domain.aggregate.recipe.infra;

import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import com.bh.rms.domain.aggregate.recipe.Recipe;

import java.util.List;

public interface RecipeRepository {
    Recipe save(Recipe recipe);
    void delete(String recipeId);
    List<Recipe> findAll();

    Recipe findById(String recipeId) throws NotFoundRecipeException;
}
