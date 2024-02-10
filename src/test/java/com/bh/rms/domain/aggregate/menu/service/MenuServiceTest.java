package com.bh.rms.domain.aggregate.menu.service;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.exception.MenuNotFoundException;
import com.bh.rms.domain.aggregate.menu.infra.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;
    @Mock
    private MenuRepository menuRepository;

    @Test
    void getWhenMenuIdIsNotExist() {
        String nonExistentMenuId = "menu1";

        when(menuRepository.findById(nonExistentMenuId)).thenThrow(MenuNotFoundException.class);

        assertThrows(MenuNotFoundException.class, () -> menuService.get(nonExistentMenuId));
        verify(menuRepository).findById(nonExistentMenuId);
    }

    @Test
    void delete() {
        String menuId = "menu1";

        menuService.delete(menuId);

        verify(menuRepository).delete(menuId);
    }

    @Test
    void deleteWhenMenuIdIsNotExist() {
        String nonExistentMenuId = "menu1";

        doThrow(MenuNotFoundException.class)
                .when(menuRepository)
                .delete(nonExistentMenuId);

        assertThrows(MenuNotFoundException.class, () -> menuService.delete(nonExistentMenuId));
        verify(menuRepository).delete(nonExistentMenuId);
    }

    @Test
    void update() {
        Menu menu = createMenu();

        menuService.update(menu);

        verify(menuRepository).update(menu);
    }

    @Test
    void updateWhenMenuIsNotExist() {
        Menu menu = createMenu();

        doThrow(MenuNotFoundException.class)
                .when(menuRepository)
                .update(menu);

        assertThrows(MenuNotFoundException.class, () -> menuService.update(menu));
    }

    private Menu createMenu() {
        return new Menu("menu1", "아메리카노", 1500, "receipeId1");
    }
}
