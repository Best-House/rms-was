package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.recipe.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CostCalculatorTest {
    private PriceRegistry priceRegistry;

    @BeforeEach
    public void setup() {
        priceRegistry = mock(PriceRegistry.class);
        when(priceRegistry.containsKey("m1")).thenReturn(true);
        when(priceRegistry.getUnitPrice("m1")).thenReturn(1.0);
        when(priceRegistry.containsKey("m2")).thenReturn(false);
//        when(priceRegistry.getUnitPrice("m2")).thenReturn(2.0);
        when(priceRegistry.containsKey("m3")).thenReturn(true);
        when(priceRegistry.getUnitPrice("m3")).thenReturn(3.0);
    }

    private List<Ingredient> makeIngredients() {
        return List.of(
                new Ingredient("m1", 1.0),
                new Ingredient("m2", 2.0),
                new Ingredient("m3", 3.0)
        );
    }

    @Test
    void getCost() {
        List<Ingredient> ingredients = makeIngredients();

        double recipeCost = CostCalculator.calculateCost(ingredients, priceRegistry);

        assertEquals(10.0, recipeCost);
    }



    @Test
    void getCostWithEmptyIngredientTest() {
        double recipeCost = CostCalculator.calculateCost(Collections.emptyList(), priceRegistry);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void getCostWithEmptyPriceMapTest() {
        List<Ingredient> ingredients = makeIngredients();

        double recipeCost = CostCalculator.calculateCost(ingredients, mock(PriceRegistry.class));

        assertEquals(0.0, recipeCost);
    }
}