package com.bh.rms.infra;

import com.bh.rms.config.AbstractLocalIntegrationTest;
import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InmemoryMaterialRepositoryTest extends AbstractLocalIntegrationTest {
    @Autowired
    InMemoryMaterialRepository inMemoryMaterialRepository;

    @Autowired
    Environment environment;

    Material material;

    @BeforeEach
    public void beforeEach() {
        material = MaterialFactory.forCreate()
                .setName("material-name")
                .setDefaultUnitPrice(1.0)
                .build();
        inMemoryMaterialRepository.create(material);
    }

    @AfterEach
    public void afterEach() {
        inMemoryMaterialRepository.delete(material.getId());
    }

    @Test
    void update() {
        material.setName("updated");
        inMemoryMaterialRepository.update(material);
    }

    @Test
    void findById() {
        Material foundMaterial = inMemoryMaterialRepository.findById(material.getId());
        assertEquals(material.getId(), foundMaterial.getId());
    }

    @Test
    void findByIds() {
        List<Material> foundMaterials = inMemoryMaterialRepository.findByIds(List.of(material.getId()));
        assertEquals(1, foundMaterials.size());
        assertEquals(material, foundMaterials.get(0));
    }

    @Test
    void findAll() {
        List<Material> foundMaterials = inMemoryMaterialRepository.findAll();
        assertTrue(foundMaterials.size() >= 1);
        assertTrue(foundMaterials.contains(material));
    }
}
