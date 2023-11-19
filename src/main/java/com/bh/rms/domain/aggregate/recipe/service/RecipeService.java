package com.bh.rms.domain.aggregate.recipe.service;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import com.bh.rms.domain.aggregate.recipe.infra.RecipeRepository;
import com.bh.rms.domain.service.exception.MaterialMismatchException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;


    public RecipeService(RecipeRepository recipeRepository, MaterialRepository materialRepository) {
        this.recipeRepository = recipeRepository;
        this.materialRepository = materialRepository;
    }

    public String create(String name, Map<String, Double> ingredients) {
        Recipe recipe = makeRecipe(name, ingredients);
        return recipeRepository.save(recipe);
    }

    public void update(String recipeId, String name, Map<String, Double> ingredients) {
        final Recipe foundRecipe = recipeRepository.findById(recipeId);
        if(foundRecipe == null) {
            throw new NotFoundRecipeException();
        }

        Recipe recipe = makeRecipe(name, ingredients);
        recipeRepository.update(recipeId, recipe);
    }

    private Recipe makeRecipe(String name, Map<String, Double> ingredients) {
        Recipe recipe = new Recipe(name, ingredients);
        List<String> materialIds = recipe.getContainedMaterialIds();

        List<Material> materials = materialRepository.findByIds(materialIds); // material service 로 추상화하기
        if(materialIds.size() != materials.size()) {
            throw new MaterialMismatchException();
        }
        return recipe;
    }

    public void delete(String recipeId) {
        recipeRepository.delete(recipeId);
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe get(String recipeId) {
        return recipeRepository.findById(recipeId);
    }
}
