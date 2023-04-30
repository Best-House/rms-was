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
    public Material create(String name, Double price, Double amount) {
        Material material = new Material(null, name);
        if(price != null && amount != null) {
            material.setPriceInfo(price, amount);
        }
        return materialRepository.save(material);
    }

    @PutMapping("/materia/{materialId}l")
    public Material update(@PathVariable String materialId, String name, Double price, Double amount) {
        Material material = materialRepository.find(materialId);
        material.setName(name);
        if(price != null && amount != null) {
            material.setPriceInfo(price, amount);
        }
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
