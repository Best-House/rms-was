package com.bh.rms.domain.aggregate.menu.service;

import com.bh.rms.domain.aggregate.menu.exception.NotFoundMenuException;
import com.bh.rms.domain.aggregate.menu.infra.MenuRepository;
import com.bh.rms.domain.aggregate.menu.request.MenuCreateRequest;
import com.bh.rms.domain.aggregate.menu.request.MenuUpdateRequest;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import com.bh.rms.domain.aggregate.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
    @InjectMocks
    private MenuService menuService;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RecipeService recipeService;

    @Test
    void createWhenRecipeIdIsNotExist() {
        String nonExistentRecipeId = "recipe1";
        when(recipeService.get(nonExistentRecipeId)).thenThrow(NotFoundRecipeException.class);

        MenuCreateRequest request = new MenuCreateRequest("name", 111, nonExistentRecipeId);
        assertThrows(NotFoundRecipeException.class, () -> {
            menuService.create(request);
        });
    }

    @Test
    void updateWhenIdIsNotExist() {
        String nonExistentMenuId = "menu1";
        when(menuRepository.findById(nonExistentMenuId)).thenThrow(NotFoundMenuException.class);

        MenuUpdateRequest request = new MenuUpdateRequest();
        assertThrows(NotFoundMenuException.class, () -> {
            menuService.update(nonExistentMenuId ,request);
        });
    }
}
