package com.hojun.service.domain.aggregate.material;

import com.hojun.service.domain.aggregate.material.exception.InvalidPriceException;

class MaterialPriceInfo {
    private final double price;
    private final double amount;

    public MaterialPriceInfo(double price, double amount) {
        if(0 > price) {
            throw new InvalidPriceException();
        }
        if(0 >= amount) {
            throw new InvalidPriceException();
        }
        this.price = price;
        this.amount = amount;
    }

    public double getUnitPrice() {
        return price / amount;
    }
}
