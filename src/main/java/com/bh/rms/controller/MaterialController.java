package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.material.exception.MaterialNotExistException;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.material.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaterialController extends AbstractApiController{
    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @PostMapping("/materials")
    public MaterialIdResponse create(@RequestBody MaterialCreateRequest params) {
        Material material = new Material(params.getName());
        Material savedMaterial = materialRepository.save(material);
        return new MaterialIdResponse(savedMaterial.getId());
    }

    @PutMapping("/materials/{materialId}")
    public MaterialIdResponse update(@PathVariable String materialId, @RequestBody MaterialCreateRequest request) {
        Material material = new Material(request.getName());
        material.setId(materialId);
        Material updatedMaterial = materialRepository.update(materialId, material);
        return new MaterialIdResponse(updatedMaterial.getId());
    }

    @DeleteMapping("/materials/{materialId}")
    public MaterialIdResponse delete(@PathVariable String materialId) {
        Material deletedMaterial = materialRepository.delete(materialId);
        return new MaterialIdResponse(deletedMaterial.getId());
    }

    @GetMapping("/materials/{materialId}")
    public Material get(@PathVariable String materialId) {
        Material material = materialRepository.findById(materialId);
        if(material == null) {
            throw new MaterialNotExistException();
        }
        return material;
    }

    @GetMapping("/materials")
    public List<Material> getAll() {
        return materialRepository.getAll();
    }

    @Data
    public static class MaterialCreateRequest {
        private String name;
    }

    @AllArgsConstructor
    @Data
    public static class MaterialIdResponse {
        private String id;
    }
}
