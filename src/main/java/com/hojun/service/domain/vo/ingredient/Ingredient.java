package com.hojun.service.domain.vo.ingredient;

import com.hojun.service.domain.aggregate.material.Material;

public record Ingredient(Material material, int amount) {
}
