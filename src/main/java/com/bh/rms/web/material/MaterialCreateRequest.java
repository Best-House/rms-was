package com.bh.rms.web.material;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MaterialCreateRequest {
    @NotBlank
    private String name;
    @Min(0)
    private Double defaultUnitPrice;

    public Material makeMaterialForCreate() {
        return MaterialFactory.forCreate()
                .setName(name)
                .setDefaultUnitPrice(defaultUnitPrice)
                .build();
    }

    public Material makeMaterialForUpdate(String materialId) {
        return MaterialFactory.forUpdate()
                .setId(materialId)
                .setName(name)
                .setDefaultUnitPrice(defaultUnitPrice)
                .build();
    }
}
