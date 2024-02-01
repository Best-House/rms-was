package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.InvalidPurchaseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseItemTest {

    @Test
    void materialIdTest() {
        assertThrows(InvalidPurchaseException.class, () ->
                new PurchaseItem(null, 10., 10., 10));

        assertThrows(InvalidPurchaseException.class, () ->
                new PurchaseItem("", 10., 10., 10));

        assertThrows(InvalidPurchaseException.class, () ->
                new PurchaseItem(" ", 10., 10., 10));
    }

    @Test
    void priceTest() {
        assertThrows(InvalidPurchaseException.class, () ->
                new PurchaseItem("material1", -10., 10., 10));
    }

    @Test
    void amountTest() {
        assertThrows(InvalidPurchaseException.class, () ->
                new PurchaseItem("material1", 10., 0, 10));

        assertThrows(InvalidPurchaseException.class, () ->
                new PurchaseItem("material1", 10., -10, 10));
    }

    @Test
    void getUnitPrice() throws Exception {
        PurchaseItem purchaseItem = new PurchaseItem("material1", 1000., 2, 10);

        double result = purchaseItem.getUnitPrice();

        assertEquals(500., result);
    }
}
