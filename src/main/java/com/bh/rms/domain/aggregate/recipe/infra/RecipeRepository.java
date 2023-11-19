package com.bh.rms.domain.aggregate.recipe.infra;

import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import com.bh.rms.domain.aggregate.recipe.Recipe;

import java.util.List;

public interface RecipeRepository {
    String save(Recipe recipe);

    void update(String recipeId, Recipe recipe);

    void delete(String recipeId);
    List<Recipe> findAll();

    Recipe findById(String recipeId) throws NotFoundRecipeException;
}
