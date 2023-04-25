package com.hojun.service.domain.aggregate.user_material_price;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.record.MaterialUnitPrice;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode(of = "id")
public class UserMaterialPrice {
    @Getter
    private final String id;

    private final Map<Material, PriceAmount> materialPriceAmountMap;

    public UserMaterialPrice(String id) {
        this.id = id;
        materialPriceAmountMap = new HashMap<>();
    }

    public void addMaterial(Material material, double price, double amount) {
        materialPriceAmountMap.put(material, new PriceAmount(price, amount));
    }

    public MaterialUnitPrice getMaterialUnitPrice() {
        final Map<Material, Double> priceMap = materialPriceAmountMap.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().getPrice() / e.getValue().getAmount()
                        )
                );
        return new MaterialUnitPrice(priceMap);
    }

    @AllArgsConstructor
    @Data
    public static class PriceAmount {
        private double price;
        private double amount;
    }
}
