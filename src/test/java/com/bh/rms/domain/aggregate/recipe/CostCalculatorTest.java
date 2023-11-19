package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.compositions.cost.CostCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CostCalculatorTest {

    @BeforeEach
    void setUp() {
    }

    private List<Ingredient> makeIngredients() {
        return Map.of("material1", 2.0, "material2", 3.0)
                .entrySet().stream()
                .map(entry -> new Ingredient(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<String, Double> makeUnitPriceMap() {
        return Map.of("material1", 1.0, "material2", 2.0);
    }

    @Test
    void getCostTest() {
        List<Ingredient> ingredients = makeIngredients();
        Map<String, Double> unitPriceMap =  makeUnitPriceMap();

        CostCalculator costCalculator = new CostCalculator(unitPriceMap);
        double recipeCost = costCalculator.calculateCost(ingredients);
        assertEquals(8.0, recipeCost);
    }

    @Test
    void getCostWithEmptyIngredientTest() {
        List<Ingredient> ingredients = Collections.emptyList();
        Map<String, Double> unitPriceMap =  makeUnitPriceMap();

        CostCalculator costCalculator = new CostCalculator(unitPriceMap);
        double recipeCost = costCalculator.calculateCost(ingredients);
        assertEquals(0.0, recipeCost);
    }

    @Test
    void getCostWithEmptyPriceMapTest() {
        List<Ingredient> ingredients = makeIngredients();
        Map<String, Double> unitPriceMap =  Collections.emptyMap();

        CostCalculator costCalculator = new CostCalculator(unitPriceMap);
        double recipeCost = costCalculator.calculateCost(ingredients);
        assertEquals(0.0, recipeCost);
    }
}