package com.hojun.service.domain.aggregate.material;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = "id")
public class Material {
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String name;

    private MaterialPriceInfo priceInfo;


    public Material(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setPriceInfo(int price, int amount) {
        this.priceInfo = new MaterialPriceInfo(price, amount);
    }

    public boolean hasPriceInfo() {
        return priceInfo != null;
    }

    public double getUnitPrice() {
        return priceInfo.getUnitPrice();
    }
}
