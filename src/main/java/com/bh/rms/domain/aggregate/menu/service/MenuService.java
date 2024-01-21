package com.bh.rms.domain.aggregate.menu.service;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.infra.MenuRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.RecipeService;
import com.bh.rms.web.menu.MenuCreateRequest;
import com.bh.rms.web.menu.MenuCreateResponse;
import com.bh.rms.web.menu.MenuResponse;
import com.bh.rms.web.menu.MenuUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RecipeService recipeService;

    public MenuService(MenuRepository menuRepository, RecipeService recipeService) {
        this.menuRepository = menuRepository;
        this.recipeService = recipeService;
    }

    public MenuCreateResponse create(@Valid MenuCreateRequest request) {
        // TODO: recipeId가 유효한지 검증은 어떻게 하는게 좋을까?
        Recipe recipe = recipeService.get(request.getRecipeId());

        Menu menu = new Menu(request.getName(), request.getPrice(), request.getRecipeId());
        String id = menuRepository.create(menu);

        return new MenuCreateResponse(id);
    }

    public MenuResponse get(String id) {
        Menu menu = menuRepository.findById(id);
        Recipe recipe = recipeService.get(menu.getRecipeId());

        return new MenuResponse(menu.getName(), menu.getPrice(), recipe);
    }

    public void update(String id, MenuUpdateRequest request) {
        // TODO: 기존 메뉴가 존재하는지에 대한 검증은 어떻게 하는게 좋을까?
        Menu menu = menuRepository.findById(id);

        menu.setName(request.getName());
        menu.setPrice(request.getPrice());
        menu.setRecipeId(request.getRecipeId());

        menuRepository.update(menu);
    }

    public void delete(String id) {
        menuRepository.delete(id);
    }

    public List<MenuResponse> getAll() {
        return menuRepository.findAll().stream()
                .map(menu -> {
                    // TODO: Menu목록을 조회하는데 receipe 조회가 불가능 할 경우 아예 API 실패로 응답하는게 맞을까?
                    Recipe recipe = recipeService.get(menu.getRecipeId());
                    return new MenuResponse(menu.getName(), menu.getPrice(), recipe);
                })
                .collect(Collectors.toList());
    }
}
