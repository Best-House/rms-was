package com.hojun.service.domain.aggregate.material;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = "id")
public class Material {
    private final String id;
    private final String name;

    public Material(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
