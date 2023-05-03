package com.hojun.service.domain.aggregate.recipe;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe("a", Collections.EMPTY_MAP).setId("recipe1");
        Recipe recipe2 = new Recipe("b", Collections.EMPTY_MAP).setId("recipe1");
        assertEquals(recipe1, recipe2);
    }
}