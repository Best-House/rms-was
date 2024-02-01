package com.bh.rms.web.menu;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.MenuFactory;
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

    public Menu makeForUpdate(String menuId) {
        return MenuFactory.forUpdate()
                .setId(menuId)
                .setName(name)
                .setPrice(price)
                .setRecipeId(recipeId)
                .build();
    }
}
