package com.hojun.service.domain.aggregate.material;

import com.hojun.service.domain.aggregate.material.exception.InvalidPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {
    @Test
    void entityEqualityTest() {
        Material material1 = new Material("material1", "m1");
        Material material2 = new Material("material1", "m2");
        assertEquals(material1, material2);
    }

    @Test
    void hasPriceTest() {
        Material material = new Material("material1", "m1");
        material.setPriceInfo(1000, 10);
        assertTrue(material.hasPriceInfo());
    }

    @Test
    void getUnitPriceTest() {
        Material material = new Material("material1", "m1");
        material.setPriceInfo(1000, 10);
        assertEquals(100, material.getUnitPrice());
    }
}