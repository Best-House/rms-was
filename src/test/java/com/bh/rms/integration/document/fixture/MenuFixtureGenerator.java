package com.bh.rms.integration.document.fixture;

import com.bh.rms.web.menu.MenuController;
import com.bh.rms.web.menu.MenuCreateRequest;
import com.bh.rms.web.menu.MenuUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MenuFixtureGenerator {

    private final MenuController menuController;
    private final RecipeFixtureGenerator recipeFixtureGenerator;
    private Set<String> menuIdSet;

    public MenuFixtureGenerator(MenuController menuController, RecipeFixtureGenerator recipeFixtureGenerator) {
        this.menuController = menuController;
        this.recipeFixtureGenerator = recipeFixtureGenerator;
        this.menuIdSet = new HashSet<>();
    }
    
    public String createMenu() {
        String menuId = menuController.create(getMenuCreateRequest()).getId();
        menuIdSet.add(menuId);
        return menuId;
    }

    public MenuCreateRequest getMenuCreateRequest() {
        MenuCreateRequest request = new MenuCreateRequest();
        String recipeId = recipeFixtureGenerator.createRecipe();
        request.setRecipeId(recipeId);
        request.setName("menu");
        request.setPrice(1000);
        return request;
    }

    public MenuUpdateRequest getMenuUpdateRequest() {
        MenuUpdateRequest request = new MenuUpdateRequest();
        String recipeId = recipeFixtureGenerator.createRecipe();
        request.setRecipeId(recipeId);
        request.setName("menu");
        request.setPrice(1000);
        return request;
    }

    public void cleanUp() {
        for(String menuId : menuIdSet) {
            deleteMenu(menuId);
        }
        menuIdSet = new HashSet<>();
    }


    public void deleteMenu(String menuId) {
        try {
            menuController.delete(menuId);
        } catch (Exception e) {
            log.warn("Delete menu failed. menuId: " + menuId + " ,message: " + e.getMessage());
        }
    }
}
