package com.hojun.service.domain.cost;

import java.util.HashMap;

public class MarketPrice {
    private final HashMap<Ingredient, Integer> priceMap;
    private final HashMap<Material, Integer> priceMap2;

    public MarketPrice() {
        this.priceMap = new HashMap<>();
        this.priceMap2 = new HashMap<>();
    }

    public void register(Material material, int price) {
        priceMap2.put(material, price);
    }
    public boolean contains(Material material) {
        return priceMap2.containsKey(material);
    }
    public int getPrice(Material material) {
        return priceMap2.getOrDefault(material, 0);
    }
}
