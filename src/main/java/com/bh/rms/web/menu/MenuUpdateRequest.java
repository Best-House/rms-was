package com.bh.rms.web.menu;

import lombok.Data;

@Data
public class MenuUpdateRequest {
    private String name;
    private Integer price;
    private String recipeId;
}
