package com.hojun.service.domain.record;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserMaterialUnitPriceTest {

    @Test
    public void containsTest() {
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Map.of("material1", 1.0));

        assertTrue(materialUnitPrice.contains("material1"));
        assertFalse(materialUnitPrice.contains(""));
    }

    @Test
    public void getPriceTest() {
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Map.of("material1", 1.0));

        assertEquals(1, materialUnitPrice.getPrice("material1"));
    }

    @Test
    public void getPriceWithUnknownMaterial() {
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Collections.EMPTY_MAP);

        assertEquals(0, materialUnitPrice.getPrice("material1"));
    }

    @Test
    public void getUnknownMaterials() {
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Map.of("knownMaterial", 1.0));
        List<String> materials = List.of("knownMaterial", "unknownMaterial");

        List<String> unknownMaterials = materialUnitPrice.getUnknownPriceMaterialIds(materials);

        assertTrue(unknownMaterials.contains("unknownMaterial"));
        assertFalse(unknownMaterials.contains("knownMaterial"));
    }
}