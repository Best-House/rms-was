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
    public MaterialCreateResponse create(@RequestBody MaterialCreateRequest params) {
        Material material = new Material(params.getName());
        String materialId = materialRepository.save(material);
        return new MaterialCreateResponse(materialId);
    }

    @PutMapping("/materials/{materialId}")
    public void update(@PathVariable String materialId, @RequestBody MaterialCreateRequest request) {
        Material material = new Material(request.getName());
        material.setId(materialId);
        materialRepository.update(materialId, material);
    }

    @PutMapping("/materials/{materialId}/default_unit_price")
    public void updateDefaultUnitPrice(
            @PathVariable String materialId,
            @RequestParam(required = false, defaultValue = "0") Double unitPrice,
            @RequestParam(required = false, defaultValue = "false") boolean unset
    ) {
        Material material = materialRepository.findById(materialId);
        if(material == null) {
            throw new MaterialNotExistException();
        }

        if(unset) {
            material.setDefaultUnitPrice(null);
        } else {
            material.setDefaultUnitPrice(unitPrice);
        }
        materialRepository.update(materialId, material);
    }

    @DeleteMapping("/materials/{materialId}")
    public void delete(@PathVariable String materialId) {
        materialRepository.delete(materialId);
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
    public static class MaterialCreateResponse {
        private String id;
    }
}
