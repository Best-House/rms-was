package com.hojun.service.domain.aggregate.material_price;

import com.hojun.service.domain.aggregate.material.Material;
import lombok.ToString;

import java.util.Map;

@ToString
public class UserMaterialUnitPrice extends MaterialUnitPrice {
    private final Map<Material, Double> priceMap;

    public UserMaterialUnitPrice(Map<Material, Double> priceMap) {
        this.priceMap = priceMap;
    }
    @Override
    protected Map<Material, Double> getPriceMap() {
        return priceMap;
    }
}
