package com.bh.rms.domain.aggregate.menu;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Menu {
    private String id;
    private String name;
    private Integer price;
    private String recipeId;

    public Menu(String id, String name, Integer price, String recipeId) {
        setId(id);
        setName(name);
        setPrice(price);
        setRecipeId(recipeId);
    }

    public void setId(String id) {
        if (StringUtils.isBlank(id)) {
            throw new InvalidAggregateIdException();
        }
        this.id = id;
    }

    public void setRecipeId(String recipeId) {
        if (StringUtils.isBlank(recipeId)) {
            throw new InvalidAggregateIdException();
        }
        this.recipeId = recipeId;
    }

    public void setPrice(Integer price) {
        if (price == null || price < 0) {
            throw new InvalidAggregateIdException();
        }
        this.price = price;
    }

    public void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new InvalidAggregateIdException();
        }
        this.name = name;
    }
}
