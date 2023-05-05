package com.bh.rms.domain.aggregate.recipe;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeTest {

    @Test
    void entityEqualityTest() {
        Recipe recipe1 = new Recipe("a", Collections.EMPTY_MAP).setId("recipe1");
        Recipe recipe2 = new Recipe("b", Collections.EMPTY_MAP).setId("recipe1");
        assertEquals(recipe1, recipe2);
    }

    @Test
    void entityIdTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", null).setId(null);
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", null).setId("");
        });

        assertThrows(InvalidAggregateIdException.class, ()->{
            Recipe recipe = new Recipe("", null).setId(" ");
        });
    }
}