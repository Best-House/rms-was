package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidMaterialException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Material { // root aggregate
    private String id; // key
    private String name; // immutable value object
    private Double defaultUnitPrice; // immutable value object

    public Material setId(String id) {
        if(id == null || id.isBlank()) {
            throw new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidMaterialException();
        }
        this.name = name;
    }

    public void setDefaultUnitPrice(Double defaultUnitPrice) {
        if(defaultUnitPrice != null && defaultUnitPrice < 0) {
            throw new InvalidMaterialException();
        }
        this.defaultUnitPrice = defaultUnitPrice;
    }

    public boolean hasDefaultUnitPrice() {
        return defaultUnitPrice != null;
    }
}
