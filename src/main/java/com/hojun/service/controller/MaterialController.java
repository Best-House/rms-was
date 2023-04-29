package com.hojun.service.controller;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.material.infra.MaterialRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaterialController {
    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @PostMapping("/material")
    public Material create(String name) {
        return materialRepository.save(new Material(null, name));
    }

    @PutMapping("/materia/{materialId}l")
    public Material update(@PathVariable String materialId, String name) {
        Material material = materialRepository.find(materialId);
        material.setName(name);
        return materialRepository.update(materialId, material);
    }

    @DeleteMapping("/material/{materialId}")
    public Material delete(@PathVariable String materialId) {
        return materialRepository.delete(materialId);
    }

    @GetMapping("/material/{materialId}")
    public Material get(@PathVariable String materialId) {
        return materialRepository.find(materialId);
    }
}
