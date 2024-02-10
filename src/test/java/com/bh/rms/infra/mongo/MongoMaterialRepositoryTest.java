package com.bh.rms.infra.mongo;

import com.bh.rms.config.AbstractProdIntegrationTest;
import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MongoMaterialRepositoryTest extends AbstractProdIntegrationTest {
    @Autowired
    MongoMaterialRepository mongoMaterialRepository;

    Material material;

    @BeforeEach
    public void beforeEach() {
        material = MaterialFactory.forCreate()
                .setName("material-name")
                .setDefaultUnitPrice(1.0)
                .build();
        mongoMaterialRepository.create(material);
    }

    @AfterEach
    public void afterEach() {
        mongoMaterialRepository.delete(material.getId());
    }

    @Test
    void update() {
        material.setName("updated");
        mongoMaterialRepository.update(material);
    }

    @Test
    void findById() {
        Material foundMaterial = mongoMaterialRepository.findById(material.getId());
        assertEquals(material.getId(), foundMaterial.getId());
    }

    @Test
    void findByIds() {
        List<Material> foundMaterials = mongoMaterialRepository.findByIds(List.of(material.getId()));
        assertEquals(1, foundMaterials.size());
        assertEquals(material, foundMaterials.get(0));
    }

    @Test
    void findAll() {
        List<Material> foundMaterials = mongoMaterialRepository.findAll();
        assertTrue(foundMaterials.size() >= 1);
        assertTrue(foundMaterials.contains(material));
    }

    @Test
    void existByIds() {
        boolean result = mongoMaterialRepository.existByIds(List.of(material.getId()));
        assertTrue(result);
    }

    @Test
    void existByIdsFailure() {
        boolean result = mongoMaterialRepository.existByIds(List.of(material.getId(), new ObjectId().toHexString()));
        assertFalse(result);
    }
}
