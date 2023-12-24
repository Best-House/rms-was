package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidIngredientAmountException;

// VO
// TODO 부동 소수점의 정확성 문제 해결하기
public record Ingredient(String materialId, double amount) {
    public Ingredient {
        if (amount <= 0.0) {
            throw new InvalidIngredientAmountException();
        }
    }
}
