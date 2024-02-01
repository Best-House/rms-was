package com.bh.rms.web.menu;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public MenuCreateResponse create(@RequestBody MenuCreateRequest request) {
        Menu menu = request.makeForCreate();
        menuService.create(menu);
        return new MenuCreateResponse(menu.getId());
    }

    @GetMapping("/menus/{menuId}")
    public Menu get(@PathVariable String menuId) {
        return menuService.get(menuId);
    }

    @PutMapping("/menus/{menuId}")
    public void update(@PathVariable String menuId, @RequestBody MenuUpdateRequest request) {
        Menu menu = request.makeForUpdate(menuId);
        menuService.update(menu);
    }

    @DeleteMapping("/menus/{menuId}")
    public void delete(@PathVariable String menuId) {
        menuService.delete(menuId);
    }

    @GetMapping("/menus")
    public List<Menu> getAll() {
        return menuService.getAll();
    }
}
