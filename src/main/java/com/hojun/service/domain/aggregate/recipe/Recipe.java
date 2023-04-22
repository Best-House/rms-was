package com.hojun.service.domain.aggregate.recipe;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.vo.ingredient.Ingredient;
import com.hojun.service.domain.vo.material_price.MaterialPrice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;
@ToString
@EqualsAndHashCode(of = "id")
public class Recipe {
    @Getter
    private final String id;
    @Getter
    private final String name;
    private final List<Ingredient> ingredients;

    public Recipe(String id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public double getCost(MaterialPrice materialPrice) {
        if (ingredients.isEmpty()) {
            return 0;
        } else {
            int result = 0;
            for(Ingredient ingredient : ingredients) {
                if(materialPrice.contains(ingredient.material())) {
                    final double pricePerAmount = materialPrice.getPrice(ingredient.material());
                    final double price = pricePerAmount * ingredient.amount();
                    result += price;
                }
            }
            return result;
        }
    }

    public List<Material> getContainedMaterials() {
        return ingredients.stream()
                .map(Ingredient::material)
                .collect(Collectors.toList());
    }
}
