package com.bh.rms.web.material;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class MaterialController{
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/materials")
    public MaterialCreateResponse create(@RequestBody MaterialCreateRequest request) {
        Material material = request.makeMaterialForCreate();
        String materialId = materialService.create(material);
        return new MaterialCreateResponse(materialId);
    }

    @PutMapping("/materials/{materialId}")
    public void update(@PathVariable String materialId,@RequestBody MaterialCreateRequest request) {
        Material material = request.makeMaterialForUpdate(materialId);
        materialService.update(material);
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
}
