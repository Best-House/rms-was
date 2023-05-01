package com.hojun.service.domain.service;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MaterialServiceTest {
    MaterialRepository materialRepository;
    MaterialService materialService;

    @BeforeEach
    public void setup() {
        materialRepository = mock(MaterialRepository.class);
        materialService = new MaterialService(materialRepository);
    }

    @Test
    public void getMaterialUnitPriceTest() {
        Material material1 = new Material("material1", "");
        material1.setPriceInfo(1, 1);
        Material material2 = new Material("material2", "");
        List<String> materialIds = List.of("material1", "material2");
        when(materialRepository.findMaterials(materialIds)).thenReturn(List.of(material1, material2));

        Map<String, Double> materialUnitPrice = materialService.getMaterialUnitPrice(materialIds);

        assertTrue(materialUnitPrice.containsKey("material1"));
        assertEquals(1, materialUnitPrice.get("material1"));
        assertFalse(materialUnitPrice.containsKey("material2"));
    }
}