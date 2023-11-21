package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
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
    public MaterialCreateResponse create(@RequestBody MaterialCreateRequest request) {
        Material material = new Material(request.getName(), request.getDefaultUnitPrice());
        String materialId = materialRepository.save(material);
        return new MaterialCreateResponse(materialId);
    }

    @PutMapping("/materials/{materialId}")
    public void update(@PathVariable String materialId, @RequestBody MaterialCreateRequest request) {
        Material material = new Material(materialId, request.getName(), request.getDefaultUnitPrice());
        materialRepository.update(materialId, material);
    }

    @DeleteMapping("/materials/{materialId}")
    public void delete(@PathVariable String materialId) {
        materialRepository.delete(materialId);
    }

    @GetMapping("/materials/{materialId}")
    public Material get(@PathVariable String materialId) {
        Material material = materialRepository.findById(materialId);
        return material;
    }

    @GetMapping("/materials")
    public List<Material> getAll() {
        return materialRepository.getAll();
    }

    @Data
    public static class MaterialCreateRequest {
        private String name;
        private Double defaultUnitPrice;
    }

    @AllArgsConstructor
    @Data
    public static class MaterialCreateResponse {
        private String id;
    }
}
