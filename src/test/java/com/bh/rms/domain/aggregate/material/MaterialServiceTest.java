package com.bh.rms.domain.aggregate.material;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialRepository;
import com.bh.rms.domain.aggregate.material.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void isAllExist() {
        Material material1 = new Material();
        material1.setId("material1");
        Material material2 = new Material();
        material2.setId("material2");

        when(materialRepository.findByIds(List.of(material1.getId(), material2.getId())))
                .thenReturn(List.of(
                        material1,
                        material2
                ));

        boolean result = materialService.isAllExist(List.of(material1.getId(), material2.getId()));
        assertTrue(result);
    }

    @Test
    void isAllExistFailure() {
        Material material1 = new Material();
        material1.setId("material1");
        Material material2 = new Material();
        material2.setId("material2");

        when(materialRepository.findByIds(List.of(material1.getId(), material2.getId())))
                .thenReturn(List.of(material1));

        boolean result = materialService.isAllExist(List.of(material1.getId(), material2.getId()));
        assertFalse(result);
    }
}