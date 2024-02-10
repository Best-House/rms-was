package com.bh.rms.domain.aggregate.menu.infra;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.exception.MenuNotFoundException;

import java.util.List;

public interface MenuRepository {
    String create(Menu menu);
    void update(Menu menu) throws MenuNotFoundException;
    void delete(String id) throws MenuNotFoundException;
    Menu findById(String id) throws MenuNotFoundException;
    List<Menu> findAll();
}
