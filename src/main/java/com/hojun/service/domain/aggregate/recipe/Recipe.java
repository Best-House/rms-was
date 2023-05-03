package com.hojun.service.domain.aggregate.recipe;

import com.hojun.service.domain.exception.InvalidAggregateIdException;
import lombok.*;

import java.util.List;
import java.util.Map;
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Recipe {
    private String id;
    private final String name;
    private final Ingredient ingredient;

    public Recipe(String name, Map<String, Double> materialIdAmountMap) {
        this.name = name;
        this.ingredient = new Ingredient(materialIdAmountMap);
    }

    public Recipe setId(String id) {
        if(id == null || id.isBlank()) {
            throw  new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public double getCost(Map<String, Double> materialUnitPriceMap) {
        return ingredient.getCost(materialUnitPriceMap);
    }

    public List<String> getContainedMaterialIds() {
        return ingredient.getContainedMaterialIds();
    }
}
