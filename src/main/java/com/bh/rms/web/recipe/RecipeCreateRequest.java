package com.bh.rms.web.recipe;

import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.RecipeFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecipeCreateRequest {
    @NotBlank
    private String name;
    @Valid
    private List<IngredientInput> ingredients;

    public List<Ingredient> getIngredients() {
        if(ingredients == null) {
            return Collections.emptyList();
        }
        return ingredients.stream()
                .map(ingredientInput -> new Ingredient(
                                ingredientInput.getMaterialId(),
                                ingredientInput.getAmount()
                        )
                ).collect(Collectors.toList());
    }

    public Recipe makeRecipeForCreate() {
        RecipeFactory.RecipeFactoryForCreate factory = RecipeFactory.forCreate()
                .setName(name);

        for(IngredientInput ingredientInput : ingredients) {
            factory.addIngredients(ingredientInput.getMaterialId(), ingredientInput.getAmount());
        }
        return factory.build();
    }

    public Recipe makeRecipeForUpdate(String recipeId) {
        RecipeFactory.RecipeFactoryForUpdate factory = RecipeFactory.forUpdate()
                .setId(recipeId)
                .setName(name);

        for(IngredientInput ingredientInput : ingredients) {
            factory.addIngredients(ingredientInput.getMaterialId(), ingredientInput.getAmount());
        }
        return factory.build();
    }

    @Data
    public static class IngredientInput {
        @NotBlank
        private String materialId;
        @Positive
        private Double amount;
    }
}
