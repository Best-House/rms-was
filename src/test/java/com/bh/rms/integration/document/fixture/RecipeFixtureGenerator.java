package com.bh.rms.integration.document.fixture;

import com.bh.rms.controller.RecipeController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class RecipeFixtureGenerator {
    private final RecipeController recipeController;
    private final MaterialFixtureGenerator materialFixtureGenerator;
    private int count;
    private Set<String> recipeIdSet;

    public RecipeFixtureGenerator(
            RecipeController recipeController,
            MaterialFixtureGenerator materialFixtureGenerator
    ) {
        this.recipeController = recipeController;
        this.materialFixtureGenerator = materialFixtureGenerator;
        count = 1;
        recipeIdSet = new HashSet<>();
    }

    public String createRecipe() {
        String recipeId = recipeController.create(getRecipeCreateRequest()).getId();
        recipeIdSet.add(recipeId);
        return recipeId;
    }

    public RecipeController.RecipeCreateRequest getRecipeCreateRequest() {
        RecipeController.RecipeCreateRequest request = new RecipeController.RecipeCreateRequest();
        request.setName("recipe" + (count++));
        request.setIngredients(getIngredientInputs());
        return request;
    }

    private List<RecipeController.IngredientInput> getIngredientInputs() {
        List<RecipeController.IngredientInput> ingredientInputs = new ArrayList<>();

        for(int i = 1; i <= 2; i++) {
            String materialId = materialFixtureGenerator.createMaterial();
            RecipeController.IngredientInput ingredientInput = new RecipeController.IngredientInput();
            ingredientInput.setMaterialId(materialId);
            ingredientInput.setAmount(10.0);
            ingredientInputs.add(ingredientInput);
        }
        return ingredientInputs;
    }

    public void cleanUp() {
        for(String recipeId : recipeIdSet) {
            deleteRecipe(recipeId);
        }
        materialFixtureGenerator.cleanUp();
        recipeIdSet = new HashSet<>();
    }

    public void deleteRecipe(String recipeId) {
        try {
            recipeController.delete(recipeId);
        } catch (Exception e) {
            log.warn("Delete recipe failed. recipeId: " + recipeId + " ,message: " + e.getMessage());
        }
    }
}
