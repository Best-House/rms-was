package com.bh.rms.domain.aggregate.menu.request;

import lombok.Data;

@Data
public class MenuUpdateRequest {
    private String name;
    private Integer price;
    private String recipeId;
}
