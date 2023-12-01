package com.bh.rms.domain.aggregate.purchase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    @Test
    void getUnitPrice() throws Exception {
        Purchase purchase = new Purchase("id", "material1", 1000., 2);

        double result = purchase.getUnitPrice();

        assertEquals(500., result);
    }
}
