package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
import com.bh.rms.domain.aggregate.recipe.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CostCalculatorTest {
    List<Material> materialList;
    @BeforeEach
    void setUp() {
        materialList = new ArrayList<>();
        materialList.add(MaterialFactory.forUpdate().setId("m1").setName("m1").setDefaultUnitPrice(2.0).build());
        materialList.add(MaterialFactory.forUpdate().setId("m2").setName("m2").setDefaultUnitPrice(3.0).build());
        materialList.add(MaterialFactory.forUpdate().setId("m3").setName("m3").setDefaultUnitPrice(4.0).build());
    }

    private List<Ingredient> getIngredients() {
        return List.of(
                new Ingredient(materialList.get(0).getId(), 1.0),
                new Ingredient(materialList.get(1).getId(), 2.0),
                new Ingredient(materialList.get(2).getId(), 3.0)
        );
    }

    private List<PurchaseItem> makePurchaseItems() {
        return List.of(
                new PurchaseItem("m1", 2.0, 2, 1),
                new PurchaseItem("m2", 2.0, 1, 1)
        );
    }

    @Test
    void getCostWithDefaultUnitPrice() {
        List<Ingredient> ingredients = getIngredients();
        List<Material> materials =  List.of(materialList.get(0), materialList.get(2));

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(14.0, recipeCost);
    }

    @Test
    void getPurchaseWithDefaultUnitPrice() {
        List<Ingredient> ingredients = getIngredients();
        List<PurchaseItem> purchaseItems = makePurchaseItems();

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putPurchaseUnitPrice(purchaseItems);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(5.0, recipeCost);
    }

    @Test
    void getUnknownPriceMaterials() {
        List<Ingredient> ingredients = getIngredients();
        List<Material> materials = List.of(materialList.get(0), materialList.get(2));

        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        List<String> unknownPriceMaterials = costCalculator.getUnknownPriceOf(
                ingredients.stream()
                        .map(Ingredient::materialId)
                        .collect(Collectors.toList())
        );

        assertTrue(unknownPriceMaterials.contains(materialList.get(1).getId()));
    }

    @Test
    void getCostWithEmptyIngredientTest() {
        List<Ingredient> ingredients = Collections.emptyList();
        List<Material> materials =  List.of(materialList.get(0), materialList.get(1), materialList.get(2));
        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(0.0, recipeCost);
    }

    @Test
    void getCostWithEmptyPriceMapTest() {
        List<Ingredient> ingredients = getIngredients();
        List<Material> materials =  Collections.emptyList();
        CostCalculator costCalculator = new CostCalculator();
        costCalculator.putDefaultUnitPriceOf(materials);

        double recipeCost = costCalculator.calculateCost(ingredients);

        assertEquals(0.0, recipeCost);
    }
}