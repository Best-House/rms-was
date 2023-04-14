package com.hojun.service.domain.cost;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialPrice {
    private final HashMap<Material, Integer> priceMap;

    public MaterialPrice() {
        this.priceMap = new HashMap<>();
    }

    public void register(Material material, int price) {
        priceMap.put(material, price);
    }
    public boolean contains(Material material) {
        return priceMap.containsKey(material);
    }
    public int getPrice(Material material) {
        return priceMap.getOrDefault(material, 0);
    }

    public List<Material> getUnknownMaterials(List<Material> materialList) {
        return materialList.stream()
                .filter(material -> !priceMap.containsKey(material))
                .collect(Collectors.toList());
    }
}
