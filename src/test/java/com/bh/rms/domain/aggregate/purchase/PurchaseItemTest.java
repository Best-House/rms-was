package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.InvalidPurchaseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseItemTest {

    @Test
    void materialIdTest() {
        PurchaseItem purchaseItem = new PurchaseItem();

        assertThrows(InvalidPurchaseException.class, () ->
                purchaseItem.setMaterialId(null));

        assertThrows(InvalidPurchaseException.class, () ->
                purchaseItem.setMaterialId(""));

        assertThrows(InvalidPurchaseException.class, () ->
                purchaseItem.setMaterialId(" "));
    }

    @Test
    void priceTest() {
        PurchaseItem purchaseItem = new PurchaseItem();

        assertThrows(InvalidPurchaseException.class, () ->
                purchaseItem.setPrice(-10));
    }

    @Test
    void amountTest() {
        PurchaseItem purchaseItem = new PurchaseItem();

        assertThrows(InvalidPurchaseException.class, () ->
                purchaseItem.setAmount(0));

        assertThrows(InvalidPurchaseException.class, () ->
                purchaseItem.setAmount(-10));
    }

    @Test
    void getUnitPrice() throws Exception {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setPrice(1000);
        purchaseItem.setAmount(2);

        double result = purchaseItem.getUnitPrice();

        assertEquals(500., result);
    }
}
