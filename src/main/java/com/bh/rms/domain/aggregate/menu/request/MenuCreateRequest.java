package com.bh.rms.domain.aggregate.menu.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuCreateRequest {
    @NotBlank
    private String name;
    @Min(0)
    private Integer price;
    @NotBlank
    private String recipeId;
}
