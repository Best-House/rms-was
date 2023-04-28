package com.hojun.service.domain.record;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.record.MaterialUnitPrice;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserMaterialUnitPriceTest {

    @Test
    public void containsTest() {
        Material material = new Material("material1","material1");
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Map.of(material, 1.0));

        assertTrue(materialUnitPrice.contains(material));
        assertFalse(materialUnitPrice.contains(new Material("", "")));
    }

    @Test
    public void getPriceTest() {
        Material material = new Material("material1", "material1");
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Map.of(material, 1.0));

        assertEquals(1, materialUnitPrice.getPrice(material));
    }

    @Test
    public void getPriceWithUnknownMaterial() {
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Collections.EMPTY_MAP);

        assertNotEquals(0, materialUnitPrice.getPrice(new Material("material1", "material1")));
    }

    @Test
    public void getUnknownMaterials() {
        Material unknownMaterial = new Material("material1", "material1");
        Material knownMaterial = new Material("material2", "material2");
        MaterialUnitPrice materialUnitPrice = new MaterialUnitPrice(Map.of(knownMaterial, 1.0));
        List<Material> materials = List.of(unknownMaterial, knownMaterial);

        List<Material> unknownMaterials = materialUnitPrice.getUnknownPriceMaterials(materials);

        assertTrue(unknownMaterials.contains(unknownMaterial));
        assertFalse(unknownMaterials.contains(knownMaterial));
    }
}