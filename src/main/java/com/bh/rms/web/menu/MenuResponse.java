package com.bh.rms.web.menu;

import com.bh.rms.domain.aggregate.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuResponse {
    private String name;
    private Integer price;
    private Recipe recipe;
}
