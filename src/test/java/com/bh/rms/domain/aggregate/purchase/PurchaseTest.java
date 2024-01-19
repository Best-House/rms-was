package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    @Test
    void entityEqualityTest() {
        Purchase purchase1 = new Purchase();
        purchase1.setId("purchase1");
        Purchase purchase2 = new Purchase();
        purchase2.setId("purchase1");
        assertEquals(purchase1, purchase2);
    }

    @Test
    void entityIdTest() {
        Purchase purchase = new Purchase();

        assertThrows(InvalidAggregateIdException.class, () ->
                purchase.setId(null));

        assertThrows(InvalidAggregateIdException.class, () ->
                purchase.setId(""));

        assertThrows(InvalidAggregateIdException.class, () ->
                purchase.setId(" "));
    }

}
