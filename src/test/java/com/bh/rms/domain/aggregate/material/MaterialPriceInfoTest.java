package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialPriceInfoTest {

    @Test
    void getUnitPriceTest() {
        MaterialPriceInfo materialPriceInfo = new MaterialPriceInfo(1000, 10);
        assertEquals(100, materialPriceInfo.getUnitPrice());
    }

    @Test
    void amountTest() {
        new MaterialPriceInfo(1000, 1);

        assertThrows(InvalidPriceException.class, ()-> {
            new MaterialPriceInfo(1000, 0);
        });
    }

    @Test
    void priceTest() {
        new MaterialPriceInfo(0, 1);
        assertThrows(InvalidPriceException.class, ()-> {
            new MaterialPriceInfo(-1, 1);
        });
    }
}