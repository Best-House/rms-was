package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.recipe.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CostCalculatorTest {

    @BeforeEach
    void setUp() {
    }

    private List<Ingredient> makeIngredients() {
        return List.of(
                new Ingredient("material1", 2.0),
                new Ingredient("material2", 3.0),
                new Ingredient("material3", 4.0)
        );
    }

    private List<Material> makeMaterials() {
        return List.of(
                new Material("material1","m1", 1.0),
                new Material("material3","m2", 2.0)
                );
    }

    @Test
    void getCostTest() {
        List<Ingredient> ingredients = makeIngredients();
        List<Material> materials =  makeMaterials();

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(10.0, recipeCost);
    }

    @Test
    void getUnknownPriceMaterials() {
        List<Ingredient> ingredients = makeIngredients();
        List<Material> materials =  makeMaterials();

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        List<String> unknownPriceMaterials = costCalculator.getUnknownPriceOf(
                ingredients.stream()
                        .map(Ingredient::materialId)
                        .collect(Collectors.toList())
        );

        assertTrue(unknownPriceMaterials.contains("material2"));
    }

    @Test
    void getCostWithEmptyIngredientTest() {
        List<Ingredient> ingredients = Collections.emptyList();
        List<Material> materials =  makeMaterials();
        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void getCostWithEmptyPriceMapTest() {
        List<Ingredient> ingredients = makeIngredients();
        List<Material> materials =  Collections.emptyList();
        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(0.0, recipeCost);
    }
}