package com.hojun.service.infra;

import com.hojun.service.domain.recipe.exception.NotFoundRecipeException;
import com.hojun.service.domain.recipe.Recipe;
import com.hojun.service.domain.recipe.infra.RecipeRepository;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        Recipe recipe = recipeProperties.getRecipes()
                .stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst()
                .orElse(null);
        if (recipe == null) {
            throw new NotFoundRecipeException();
        }
        return recipe;
    }

    @Data
    @ConfigurationProperties("recipe")
    public static class RecipeProperties {
        private List<Recipe> recipes;
    }
}
