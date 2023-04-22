package com.hojun.service.domain.material_price;

import com.hojun.service.domain.material.Material;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
