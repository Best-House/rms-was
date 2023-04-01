package com.hojun.service.domain.cost;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketPriceTest {

    @Test
    public void containsTest() {
        Material material = new Material();
        MarketPrice marketPrice = new MarketPrice();
        marketPrice.register(material, 1);

        assertTrue(marketPrice.contains(material));
    }

    @Test
    public void getPriceTest() {
        Material material = new Material();
        MarketPrice marketPrice = new MarketPrice();
        marketPrice.register(material, 1);

        assertEquals(1, marketPrice.getPrice(material));
    }

    @Test
    public void getPriceWithUnknownMaterial() {
        MarketPrice marketPrice = new MarketPrice();

        assertEquals(0, marketPrice.getPrice(new Material()));
    }

    @Test
    public void getUnknownMaterials() {
    }
}