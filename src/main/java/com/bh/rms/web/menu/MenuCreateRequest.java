package com.bh.rms.web.menu;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.MenuFactory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuCreateRequest {
    @NotBlank
    private String name;
    @Min(0)
    private Integer price;
    @NotBlank
    private String recipeId;

    public Menu makeForCreate() {
        return MenuFactory.forCreate()
                .setName(name)
                .setPrice(price)
                .setRecipeId(recipeId)
                .build();
    }
}
