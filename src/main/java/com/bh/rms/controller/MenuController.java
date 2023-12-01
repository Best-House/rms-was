package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.menu.request.MenuCreateRequest;
import com.bh.rms.domain.aggregate.menu.request.MenuUpdateRequest;
import com.bh.rms.domain.aggregate.menu.response.MenuCreateResponse;
import com.bh.rms.domain.aggregate.menu.response.MenuResponse;
import com.bh.rms.domain.aggregate.menu.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController extends AbstractApiController{
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public MenuCreateResponse create(@RequestBody MenuCreateRequest request) {
        return menuService.create(request);
    }

    @GetMapping("/menus/{menuId}")
    public MenuResponse get(@PathVariable String menuId) {
        return menuService.get(menuId);
    }

    @PutMapping("/menus/{menuId}")
    public void update(@PathVariable String menuId, @RequestBody MenuUpdateRequest request) {
        menuService.update(menuId, request);
    }

    @DeleteMapping("/menus/{menuId}")
    public void delete(@PathVariable String menuId) {
        menuService.delete(menuId);
    }

    @GetMapping("/menus")
    public List<MenuResponse> getAll() {
        return menuService.getAll();
    }
}
