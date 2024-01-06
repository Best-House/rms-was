package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.exception.InvalidMaterialException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {
    @Test
    void entityEqualityTest() {
        Material material1 = new Material();
        Material material2 = new Material();
        material1.setId("material1");
        material1.setName("material1");
        material2.setId("material1");
        material2.setName("material2");
        assertEquals(material1, material2);
    }

    @Test
    void entityIdTest() {
        Material material = new Material();
        material.setId("material_1");

        assertThrows(InvalidAggregateIdException.class, ()->{
            material.setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            material.setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            material.setId(" ");
        });
    }

    @Test
    void setName() {
        Material material = new Material();
        material.setName("material1");

        assertThrows(InvalidMaterialException.class, ()->{
            material.setName(null);
        });

        assertThrows(InvalidMaterialException.class, ()->{
            material.setName("");
        });

        assertThrows(InvalidMaterialException.class, ()->{
            material.setName(" ");
        });
    }

    @Test
    void setDefaultUnitPrice() {
        Material material = new Material();
        material.setDefaultUnitPrice(1.0);
        material.setDefaultUnitPrice(null);

        assertThrows(InvalidMaterialException.class, ()->{
            material.setDefaultUnitPrice(-1.0);
        });
    }

    @Test
    void hasDefaultUnitPrice() {
        Material materialWithDefaultPrice = new Material();
        materialWithDefaultPrice.setDefaultUnitPrice(1.0);
        assertTrue(materialWithDefaultPrice.hasDefaultUnitPrice());

        Material materialWithoutDefaultPrice = new Material();
        assertFalse(materialWithoutDefaultPrice.hasDefaultUnitPrice());
    }
}