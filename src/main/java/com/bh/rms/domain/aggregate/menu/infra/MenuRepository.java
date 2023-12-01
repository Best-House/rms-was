package com.bh.rms.domain.aggregate.menu.infra;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.exception.NotFoundMenuException;

import java.util.List;

public interface MenuRepository {
    String create(Menu menu);
    void update(Menu menu) throws NotFoundMenuException;
    void delete(String id) throws NotFoundMenuException;
    Menu findById(String id) throws NotFoundMenuException;
    List<Menu> findAll();
}
