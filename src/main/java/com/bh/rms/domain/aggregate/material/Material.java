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
    private final Double defaultUnitPrice;

    public Material(String name, Double defaultUnitPrice) {
        this.name = name;
        this.defaultUnitPrice = defaultUnitPrice;
        if(defaultUnitPrice != null && defaultUnitPrice < 0) {
            throw new InvalidPriceException();
        }
    }

    public Material setId(String id) {
        if(id == null || id.isBlank()) {
            throw  new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public boolean hasDefaultUnitPrice() {
        return defaultUnitPrice != null;
    }
}
