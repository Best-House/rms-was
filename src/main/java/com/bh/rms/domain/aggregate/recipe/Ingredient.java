package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;
import lombok.Getter;

public record Ingredient(String materialId, double amount) {
    public Ingredient(String materialId, double amount) {
        this.materialId = materialId;
        this.amount = amount;

        if (amount <= 0.0) {
            throw new InvalidIngredientAmountException();
        }
    }
}
