package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidMaterialException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Material {
    private String id;
    private String name;
    private Double defaultUnitPrice;

    public Material(String name, Double defaultUnitPrice) {
        setName(name);
        setDefaultUnitPrice(defaultUnitPrice);
    }
    public Material(String id, String name, Double defaultUnitPrice) {
        setId(id);
        setName(name);
        setDefaultUnitPrice(defaultUnitPrice);
    }

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
