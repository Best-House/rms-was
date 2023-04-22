package com.hojun.service.domain.vo.material_price;

import com.hojun.service.domain.aggregate.material.Material;
import lombok.ToString;

import java.util.Map;

@ToString
public class FixedMaterialPrice extends MaterialPrice{
    private final Map<Material, Double> priceMap;

    public FixedMaterialPrice(Map<Material, Double> priceMap) {
        this.priceMap = priceMap;
    }
    @Override
    protected Map<Material, Double> getPriceMap() {
        return priceMap;
    }
}
