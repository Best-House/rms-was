package com.hojun.service.domain.aggregate.material_price;

import com.hojun.service.domain.aggregate.material.Material;
import lombok.ToString;

import java.util.Map;

@ToString
public class FixedMaterialUnitPrice extends MaterialUnitPrice {
    private final Map<Material, Double> priceMap;

    public FixedMaterialUnitPrice(Map<Material, Double> priceMap) {
        this.priceMap = priceMap;
    }
    @Override
    protected Map<Material, Double> getPriceMap() {
        return priceMap;
    }
}
