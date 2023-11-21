package com.bh.rms.domain.aggregate.recipe.infra;

import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;

import java.util.List;

public interface RecipeRepository {
    String save(Recipe recipe);
    void update(String recipeId, Recipe recipe) throws NotFoundRecipeException;
    void delete(String recipeId) throws NotFoundRecipeException;
    Recipe findById(String recipeId) throws NotFoundRecipeException;
    List<Recipe> findAll();
}
