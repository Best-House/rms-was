package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.*;

import java.util.List;
import java.util.Map;
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Recipe {
    private String id;
    private final String name;
    private final Ingredients ingredients;

    public Recipe(String name, Map<String, Double> materialIdAmountMap) {
        this.name = name;
        this.ingredients = new Ingredients(materialIdAmountMap);
    }

    public Recipe setId(String id) {
        if(id == null || id.isBlank()) {
            throw  new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public double getCost(Map<String, Double> materialUnitPriceMap) {
        return ingredients.calculateCost(materialUnitPriceMap);
    }

    public List<String> getContainedMaterialIds() {
        return ingredients.getContainedMaterialIds();
    }
}
