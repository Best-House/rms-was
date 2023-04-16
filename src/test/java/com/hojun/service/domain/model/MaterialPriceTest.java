package com.hojun.service.domain.model;

import com.hojun.service.domain.model.Material;
import com.hojun.service.domain.model.MaterialPrice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaterialPriceTest {

    @Test
    public void containsTest() {
        Material material = new Material();
        MaterialPrice materialPrice = new MaterialPrice();
        materialPrice.register(material, 1);

        assertTrue(materialPrice.contains(material));
    }

    @Test
    public void getPriceTest() {
        Material material = new Material();
        MaterialPrice materialPrice = new MaterialPrice();
        materialPrice.register(material, 1);

        assertEquals(1, materialPrice.getPrice(material));
    }

    @Test
    public void getPriceWithUnknownMaterial() {
        MaterialPrice materialPrice = new MaterialPrice();

        assertEquals(0, materialPrice.getPrice(new Material()));
    }

    @Test
    public void getUnknownMaterials() {
        Material unknownMaterial = new Material();
        Material knownMaterial = new Material();
        MaterialPrice materialPrice = new MaterialPrice();
        materialPrice.register(knownMaterial, 1);
        List<Material> materials = List.of(unknownMaterial, knownMaterial);

        List<Material> unknownMaterials = materialPrice.getUnknownPriceMaterials(materials);

        assertTrue(unknownMaterials.contains(unknownMaterial));
        assertFalse(unknownMaterials.contains(knownMaterial));
    }
}