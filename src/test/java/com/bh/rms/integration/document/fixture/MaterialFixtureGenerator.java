package com.bh.rms.integration.document.fixture;

import com.bh.rms.web.controller.MaterialController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MaterialFixtureGenerator {
    private final MaterialController materialController;
    private int count;
    private Set<String> materialIdSet;

    public MaterialFixtureGenerator(MaterialController materialController) {
        this.materialController = materialController;
        count = 1;
        materialIdSet = new HashSet<>();
    }

    public String createMaterial() {
        String materialId = materialController.create(getMaterialCreateRequest()).getId();
        materialIdSet.add(materialId);
        return materialId;
    }

    public MaterialController.MaterialCreateRequest getMaterialCreateRequest() {
        MaterialController.MaterialCreateRequest materialCreateRequest = new MaterialController.MaterialCreateRequest();
        materialCreateRequest.setName("material" + (count++));
        materialCreateRequest.setDefaultUnitPrice(1.0);
        return materialCreateRequest;
    }

    public void cleanUp() {
        for(String materialId : materialIdSet) {
            deleteMaterial(materialId);
        }
        materialIdSet = new HashSet<>();
    }


    public void deleteMaterial(String materialId) {
        try {
            materialController.delete(materialId);
        } catch (Exception e) {
            log.warn("Delete material failed. materialId: " + materialId + " ,message: " + e.getMessage());
        }
    }
}
