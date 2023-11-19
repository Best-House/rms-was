package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidPriceException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Material {
    private String id;
    private final String name;
    private Double unitPrice;

    public Material(String name) {
        this.name = name;
    }

    public Material setId(String id) {
        if(id == null || id.isBlank()) {
            throw  new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public void setDefaultUnitPrice(Double unitPrice) {
        if(unitPrice < 0) {
            throw new InvalidPriceException();
        }
        this.unitPrice = unitPrice;
    }

    public boolean hasDefaultUnitPrice() {
        return unitPrice != null;
    }
}
