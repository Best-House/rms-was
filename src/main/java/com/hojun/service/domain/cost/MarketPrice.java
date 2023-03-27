package com.hojun.service.domain.cost;

import java.util.HashMap;

public class MarketPrice {
    private final HashMap<Ingredient, Integer> priceMap;

    public MarketPrice() {
        this.priceMap = new HashMap<>();
    }

    public void register(Ingredient ingredient, int price) {
        priceMap.put(ingredient, price);
    }

    public boolean contains(Ingredient ingredient) {
        return priceMap.containsKey(ingredient);
    }

    public int getPrice(Ingredient ingredient) {
        return priceMap.getOrDefault(ingredient, 0);
    }
}
