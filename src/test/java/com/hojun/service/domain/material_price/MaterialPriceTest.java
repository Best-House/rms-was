package com.hojun.service.domain.material_price;

import com.hojun.service.domain.material.Material;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MaterialPriceTest {

    @Test
    public void containsTest() {
        Material material = new Material("material1","material1");
        MaterialPrice materialPrice = new MaterialPrice(Map.of(material, 1.0));

        assertTrue(materialPrice.contains(material));
        assertFalse(materialPrice.contains(new Material("", "")));
    }

    @Test
    public void getPriceTest() {
        Material material = new Material("material1", "material1");
        MaterialPrice materialPrice = new MaterialPrice(Map.of(material, 1.0));

        assertEquals(1, materialPrice.getPrice(material));
    }

    @Test
    public void getPriceWithUnknownMaterial() {
        MaterialPrice materialPrice = new MaterialPrice(Collections.EMPTY_MAP);

        assertEquals(0, materialPrice.getPrice(new Material("material1", "material1")));
    }

    @Test
    public void getUnknownMaterials() {
        Material unknownMaterial = new Material("material1", "material1");
        Material knownMaterial = new Material("material2", "material2");
        MaterialPrice materialPrice = new MaterialPrice(Map.of(knownMaterial, 1.0));
        List<Material> materials = List.of(unknownMaterial, knownMaterial);

        List<Material> unknownMaterials = materialPrice.getUnknownPriceMaterials(materials);

        assertTrue(unknownMaterials.contains(unknownMaterial));
        assertFalse(unknownMaterials.contains(knownMaterial));
    }
}