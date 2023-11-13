package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}