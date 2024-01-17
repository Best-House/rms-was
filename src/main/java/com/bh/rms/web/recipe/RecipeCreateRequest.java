package com.bh.rms.web.recipe;

import com.bh.rms.domain.aggregate.recipe.Ingredient;
import com.bh.rms.domain.aggregate.recipe.Recipe;
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

    @Data
    public static class IngredientInput {
        @NotBlank
        private String materialId;
        @Positive
        private Double amount;
    }
}
