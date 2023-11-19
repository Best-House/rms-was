package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.compositions.cost.CostCalculator;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Recipe {
    private String id;
    private final String name;
    private final List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        if(ingredients == null) {
            this.ingredients = Collections.emptyList();
        } else {
            this.ingredients = ingredients;
        }
    }

    public Recipe setId(String id) {
        if(id == null || id.isBlank()) {
            throw  new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public double getCost(CostCalculator costCalculator) {
        return costCalculator.calculateCost(ingredients);
    }

    public List<String> getMaterialIdsOfIngredients() {
        return ingredients.stream()
                .map(Ingredient::materialId)
                .collect(Collectors.toList());
    }
}
