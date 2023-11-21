package com.bh.rms.domain.aggregate.recipe;

public record Ingredient(String materialId, double amount) {
    // TODO 부동 소수점의 정확성 문제 해결하기
    public boolean isValidAmount() {
        return !(amount <= 0.0);
    }
}
