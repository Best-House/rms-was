package com.bh.rms.domain.aggregate.menu.service;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.infra.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void create(Menu menu) {
        menuRepository.create(menu);
    }

    public void update(Menu menu) {
        menuRepository.update(menu);
    }

    public void delete(String id) {
        menuRepository.delete(id);
    }

    public Menu get(String id) {
        return menuRepository.findById(id);
    }

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }
}
