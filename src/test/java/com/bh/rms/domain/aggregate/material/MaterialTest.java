package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidPriceException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaterialTest {
    @Test
    void entityEqualityTest() {
        Material material1 = new Material("m1", 0.0);
        Material material2 = new Material("m2", 0.0);

        material1.setId("material");
        material2.setId("material");
        assertEquals(material1, material2);
    }

    @Test
    void entityIdTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            Material material1 = new Material("", 0.0).setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Material material1 = new Material("", 0.0).setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Material material1 = new Material("", 0.0).setId(" ");
        });
    }

    @Test
    void setDefaultUnitPrice() {
        Material material1 = new Material("", null);
        Material material2 = new Material("", 1.0);

        assertThrows(InvalidPriceException.class, ()->{
            Material material3 = new Material("", -1.0);
        });
    }
}