package com.hojun.service.domain.cost;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketPriceTest {

    @Test
    public void containsTest() {
        Ingredient ingredient = new Ingredient(1);
        MarketPrice marketPrice = new MarketPrice();
        marketPrice.register(ingredient, 1);

        assertTrue(marketPrice.contains(ingredient));
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(1);
        MarketPrice marketPrice = new MarketPrice();
        marketPrice.register(ingredient, 1);

        assertEquals(1, marketPrice.getPrice(ingredient));
    }

    @Test
    public void getPriceWithUnknownItem() {
        MarketPrice marketPrice = new MarketPrice();

        assertEquals(0, marketPrice.getPrice(new Ingredient(0)));
    }

    @Test
    public void getUnknownMaterials() {
    }
}