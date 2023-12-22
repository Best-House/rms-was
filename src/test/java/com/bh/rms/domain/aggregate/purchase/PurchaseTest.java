package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.InvalidPurchaseException;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    @Test
    void entityEqualityTest() {
        Purchase purchase1 = new Purchase("purchase1", "a", 100, 2, 0);
        Purchase purchase2 = new Purchase("purchase1", "b", 200, 1, 1);
        assertEquals(purchase1, purchase2);
    }

    @Test
    void entityIdTest() {
        new Purchase("purchase1", "material1", 100, 1, 1);

        assertThrows(InvalidAggregateIdException.class, () ->
                new Purchase(null, "material1", 100, 1, 1));

        assertThrows(InvalidAggregateIdException.class, () ->
                new Purchase("", "material1", 100, 1, 1));

        assertThrows(InvalidAggregateIdException.class, () ->
                new Purchase(" ", "material1", 100, 1, 1));
    }

    @Test
    void materialIdTest() {
        new Purchase("purchase1", "material1", 100, 1, 1);

        assertThrows(InvalidPurchaseException.class, () ->
                new Purchase("purchase1", null, 100, 1, 1));

        assertThrows(InvalidPurchaseException.class, () ->
                new Purchase("purchase1", "", 100, 1, 1));

        assertThrows(InvalidPurchaseException.class, () ->
                new Purchase("purchase1", " ", 100, 1, 1));
    }

    @Test
    void priceTest() {
        new Purchase("purchase1", "material1", 100, 1, 1);

        assertThrows(InvalidPurchaseException.class, () ->
                new Purchase("purchase1", "material1", -10, 1, 1));
    }

    @Test
    void amountTest() {
        new Purchase("purchase1", "material1", 100, 1, 1);

        assertThrows(InvalidPurchaseException.class, () ->
                new Purchase("purchase1", "material1", 10, 0, 1));

        assertThrows(InvalidPurchaseException.class, () ->
                new Purchase("purchase1", "material1", 10, -1, 1));
    }

    @Test
    void getUnitPrice() throws Exception {
        Purchase purchase = new Purchase("id", "material1", 1000., 2, 1);

        double result = purchase.getUnitPrice();

        assertEquals(500., result);
    }

    @Test
    void update() throws Exception {
        Purchase purchase = new Purchase("purchase", "material1", 100, 1, 1);

        purchase.update("materialId2", 200, 2, 100);
        
        assertAll(
                () -> assertEquals(purchase.getMaterialId(), "materialId2"),
                () -> assertEquals(purchase.getPrice(), 200),
                () -> assertEquals(purchase.getAmount(), 2),
                () -> assertEquals(purchase.getPurchaseDate(), 100)
        );
    }
}
