package com.bh.rms.domain.aggregate.menu;

import com.bh.rms.domain.exception.InvalidAggregateIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuTest {

    @Test
    public void entityEqualityTest() {
        Menu menu1 = new Menu("menu1", "아메리카노", 4000, "recipe1");
        Menu menu2 = new Menu("menu1", "라떼", 3000, "recipe2");
        assertEquals(menu1, menu2);
    }

    @Test
    public void entityIdValidationTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu(null, "아메리카노", 4000, "recipe1");
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("", "아메리카노", 4000, "recipe1");
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu(" ", "아메리카노", 4000, "recipe1");
        });
    }

    @Test
    public void entityNameValidationTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", null, 4000, "recipe1");
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", "", 4000, "recipe1");
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", " ", 4000, "recipe1");
        });
    }

    @Test
    public void entityPriceValidationTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", "아메리카노", null, "recipe1");
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", "", -1, "recipe1");
        });
    }

    @Test
    public void entityRecipeValidationTest() {
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", "아메리카노", 4000, null);
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", "아메리카노", 4000, "");
        });
        assertThrows(InvalidAggregateIdException.class, ()->{
            new Menu("menu1", "아메리카노", 4000, " ");
        });
    }
}
