package com.bh.rms.domain.aggregate.recipe;

public record Ingredient(String materialId, double amount) {

    public boolean isValidAmount() {
        return !(amount <= 0.0);
    }
}
