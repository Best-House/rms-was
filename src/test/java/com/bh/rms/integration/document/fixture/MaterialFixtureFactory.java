package com.bh.rms.integration.document.fixture;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MaterialFixtureFactory {
    private MaterialService materialService;
    private int count;

    public MaterialFixtureFactory(MaterialService materialService) {
        this.materialService = materialService;
        count = 0;
    }

    public String makeMaterial() {
        return materialService.create("material" + count++, 1.0);
    }

    public void deleteMaterial(String materialId) {
        try {
            materialService.delete(materialId);
        } catch (Exception e) {
            log.warn("[MaterialFixtureFactory] material delete failed. materialId: " + materialId + " , message: " + e.getMessage());
        }
    }
}
