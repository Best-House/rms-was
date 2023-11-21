package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidMaterialException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaterialTest {
    @Test
    void entityEqualityTest() {
        Material material1 = new Material("material","m1", 0.0);
        Material material2 = new Material("material","m2", 0.0);
        assertEquals(material1, material2);
    }

    @Test
    void entityIdTest() {
        new Material("material_1", "m1", 0.0);
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Material(null, "m1", 0.0);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            new Material("","m1", 0.0);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            new Material(" ", "m1", 0.0);
        });
    }

    @Test
    void setName() {
        new Material("material", "m1", 1.0);

        assertThrows(InvalidMaterialException.class, ()->{
            new Material("material", null, 1.0);
            new Material("material", "", 1.0);
        });
    }

    @Test
    void setDefaultUnitPrice() {
        new Material("m1", 1.0);

        assertThrows(InvalidMaterialException.class, ()->{
            new Material("m1", null);
            new Material("m1", -1.0);
        });
    }
}