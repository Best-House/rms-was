package com.bh.rms.domain.aggregate.material.service;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
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
        when(materialRepository.findByIds(List.of("material1", "material2")))
                .thenReturn(List.of(
                        new Material("material1", ",1", 1.0),
                        new Material("material2", ",2", 2.0)
                ));

        boolean result = materialService.isAllExist(List.of("material1", "material2"));
        assertTrue(result);
    }

    @Test
    void isAllExistFailure() {
        when(materialRepository.findByIds(List.of("material1", "material2")))
                .thenReturn(List.of(
                        new Material("material2", ",2", 2.0)
                ));

        boolean result = materialService.isAllExist(List.of("material1", "material2"));
        assertFalse(result);
    }
}