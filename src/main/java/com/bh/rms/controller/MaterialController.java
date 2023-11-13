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
    public String create(@RequestBody MaterialCreateRequest params) {
        Material material = new Material(params.getName());
        return materialRepository.save(material).getId();
    }

    @PutMapping("/materials/{materialId}")
    public String update(@PathVariable String materialId, @RequestBody MaterialCreateRequest params) {
        Material material = new Material(params.getName());
        material.setId(materialId);
        return materialRepository.update(materialId, material).getId();
    }

    @DeleteMapping("/materials/{materialId}")
    public String delete(@PathVariable String materialId) {
        return materialRepository.delete(materialId).getId();
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
}
