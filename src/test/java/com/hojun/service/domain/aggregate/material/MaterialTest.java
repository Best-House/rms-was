package com.hojun.service.domain.aggregate.material;

import com.hojun.service.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {
    @Test
    void entityEqualityTest() {
        Material material1 = new Material("m1");
        Material material2 = new Material("m2");

        material1.setId("material");
        material2.setId("material");
        assertEquals(material1, material2);
    }

    @Test
    void entityIdTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            Material material1 = new Material("").setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Material material1 = new Material("").setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Material material1 = new Material("").setId(" ");
        });
    }

    @Test
    void hasPriceTest() {
        Material material = new Material("m1");
        material.setPriceInfo(1000, 10);
        assertTrue(material.hasPriceInfo());
        
        assertFalse(new Material("m2").hasPriceInfo());
    }

    @Test
    void getUnitPriceTest() {
        Material material = new Material("m1");
        material.setPriceInfo(1000, 10);
        assertEquals(100, material.getUnitPrice());
    }
}