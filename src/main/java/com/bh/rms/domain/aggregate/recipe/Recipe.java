package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.aggregate.recipe.exception.InvalidRecipeException;
import com.bh.rms.domain.compositions.cost.CostCalculator;
import com.bh.rms.domain.compositions.cost.PriceRegistry;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Recipe { // root aggregate
    private String id; // key
    private String name; // immutable value object
    private List<Ingredient> ingredients; // immutable value object

    public Recipe() {
        setIngredients(Collections.emptyList());
    }

    public Recipe setId(String id) {
        if(id == null || id.isBlank()) {
            throw  new InvalidAggregateIdException();
        }
        this.id = id;
        return this;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidRecipeException();
        }
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        if(ingredients == null) {
            this.ingredients = Collections.emptyList();
        } else {
            this.ingredients = List.copyOf(ingredients); // immutable list
        }
    }

    public List<String> getMaterialIdsOfIngredients() {
        return ingredients.stream()
                .map(Ingredient::materialId)
                .collect(Collectors.toList());
    }

    public double calculateCost(PriceRegistry priceRegistry) {
        return CostCalculator.calculateCost(ingredients, priceRegistry);
    }
}
