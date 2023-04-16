package com.hojun.service.infra;

import com.hojun.service.domain.recipe.exception.NotFoundRecipeException;
import com.hojun.service.domain.recipe.Recipe;
import com.hojun.service.domain.recipe.infra.RecipeRepository;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.Map;

@EnableConfigurationProperties(YmlRecipeRepository.RecipeProperties.class)
@Repository
public class YmlRecipeRepository implements RecipeRepository {
    private final RecipeProperties recipeProperties;

    public YmlRecipeRepository(RecipeProperties recipeProperties) {
        this.recipeProperties = recipeProperties;
    }

    @Override
    public Recipe getRecipe(String recipeId) throws NotFoundRecipeException {
        Recipe recipe = recipeProperties.getRecipeMap().get(recipeId);
        if (recipe == null) {
            throw new NotFoundRecipeException();
        }
        return recipe;
    }

    @Data
    @ConfigurationProperties("recipe")
    public static class RecipeProperties {
        private Map<String, Recipe> recipeMap;
    }
}
