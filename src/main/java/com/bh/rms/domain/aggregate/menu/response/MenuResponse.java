package com.bh.rms.domain.aggregate.menu.response;

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
