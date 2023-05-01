package com.hojun.service.infra;

import com.hojun.service.domain.aggregate.recipe.Recipe;
import com.hojun.service.domain.aggregate.recipe.exception.NotFoundRecipeException;
import com.hojun.service.domain.aggregate.recipe.infra.RecipeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Primary
public class InMemoryRecipeRepository implements RecipeRepository {
    private final Map<String, Recipe> recipeMap;

    public InMemoryRecipeRepository() {
        recipeMap = new HashMap<>();
    }
    @Override
    public Recipe getRecipe(String recipeId) throws NotFoundRecipeException {
        return null;
    }
}
