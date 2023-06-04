package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.material.exception.MaterialNotExistException;
import com.bh.rms.domain.aggregate.material.infra.MaterialRepository;
import com.bh.rms.domain.aggregate.material.Material;
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
    public Material create(@RequestBody MaterialCreateParams params) {
        Material material = new Material(params.getName());
        if(params.hasPriceInfo()) {
            material.setPriceInfo(params.getPrice(), params.getAmount());
        }
        return materialRepository.save(material);
    }

    @PutMapping("/materials/{materialId}")
    public Material update(@PathVariable String materialId, @RequestBody MaterialCreateParams params) {
        Material material = new Material(params.getName());
        material.setId(materialId);
        if(params.hasPriceInfo()) {
            material.setPriceInfo(params.getPrice(), params.getAmount());
        }
        return materialRepository.update(materialId, material);
    }

    @DeleteMapping("/materials/{materialId}")
    public Material delete(@PathVariable String materialId) {
        return materialRepository.delete(materialId);
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
    public static class MaterialCreateParams {
        private String name;
        private Double price;
        private Double amount;

        public boolean hasPriceInfo() {
            return price != null && amount != null;
        }
    }
}
