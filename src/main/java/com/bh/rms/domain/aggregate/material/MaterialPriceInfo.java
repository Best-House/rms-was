package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidPriceException;
import lombok.ToString;

@ToString
class MaterialPriceInfo {
    private final double price;
    private final double amount;

    public MaterialPriceInfo(double price, double amount) {
        if(0 >= price) {
            throw new InvalidPriceException();
        }
        if(0 > amount) {
            throw new InvalidPriceException();
        }
        this.price = price;
        this.amount = amount;
    }

    public double getUnitPrice() {
        return price / amount;
    }
}
