package com.bh.rms.web.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuUpdateRequest {
    @NotBlank
    private String name;
    @Min(0)
    private Integer price;
    @NotBlank
    private String recipeId;
}
