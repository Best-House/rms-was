package com.bh.rms.domain.aggregate.material;

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
}
