package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.service.MaterialService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaterialController extends AbstractApiController{
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/materials")
    public MaterialCreateResponse create(@RequestBody MaterialCreateRequest request) {
        String materialId = materialService.create(request.getName(), request.getDefaultUnitPrice());
        return new MaterialCreateResponse(materialId);
    }

    @PutMapping("/materials/{materialId}")
    public void update(@PathVariable String materialId, @RequestBody MaterialCreateRequest request) {
        materialService.update(materialId, request.getName(), request.getDefaultUnitPrice());
    }

    @DeleteMapping("/materials/{materialId}")
    public void delete(@PathVariable String materialId) {
        materialService.delete(materialId);
    }

    @GetMapping("/materials/{materialId}")
    public Material get(@PathVariable String materialId) {
        return materialService.get(materialId);
    }

    @GetMapping("/materials")
    public List<Material> getAll() {
        return materialService.getAll();
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
