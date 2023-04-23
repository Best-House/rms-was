package com.hojun.service.domain.recipe;

import com.hojun.service.domain.material.Material;

public record Ingredient(Material material, int amount) {
}
