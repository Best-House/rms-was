package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompoundPriceRegistryTest {
    CompoundPriceRegistry compoundPriceRegistry = new CompoundPriceRegistry();


    @Test
    void putDefaultUnitPriceOf() {
        Material material = new Material();
        material.setId("m1");
        material.setDefaultUnitPrice(1.0);

        compoundPriceRegistry.putDefaultUnitPriceOf(List.of(material));

        assertTrue(compoundPriceRegistry.containsKey("m1"));
        assertEquals(compoundPriceRegistry.getUnitPrice("m1"), 1.0);
    }

    @Test
    void putPurchaseUnitPrice() {
        PurchaseItem purchaseItem = new PurchaseItem("m1", 2.0, 1.0);

        compoundPriceRegistry.putPurchaseUnitPrice(List.of(purchaseItem));

        assertTrue(compoundPriceRegistry.containsKey("m1"));
        assertEquals(compoundPriceRegistry.getUnitPrice("m1"), 2.0);
    }

    @Test
    void overwriteUnitPrice() {
        Material material = new Material();
        material.setId("m1");
        material.setDefaultUnitPrice(1.0);

        PurchaseItem purchaseItem = new PurchaseItem("m1", 2.0, 1.0);

        compoundPriceRegistry.putDefaultUnitPriceOf(List.of(material));
        compoundPriceRegistry.putPurchaseUnitPrice(List.of(purchaseItem));

        assertTrue(compoundPriceRegistry.containsKey("m1"));
        assertNotEquals(compoundPriceRegistry.getUnitPrice("m1"), 1.0);
        assertEquals(compoundPriceRegistry.getUnitPrice("m1"), 2.0);
    }


    @Test
    void getUnknownPriceOf() {
        Material material = new Material();
        material.setId("m2");
        material.setDefaultUnitPrice(1.0);

        compoundPriceRegistry.putDefaultUnitPriceOf(List.of(material));
        List<String> unknownPriceMaterialIds = compoundPriceRegistry.getUnknownPriceOf(List.of("m1", "m2", "m3"));

        assertEquals(List.of("m1", "m3"), unknownPriceMaterialIds);
    }
}