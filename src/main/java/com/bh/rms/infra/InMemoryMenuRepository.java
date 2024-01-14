package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.menu.Menu;
import com.bh.rms.domain.aggregate.menu.exception.MenuNotFoundException;
import com.bh.rms.domain.aggregate.menu.infra.MenuRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMenuRepository implements MenuRepository {
    private final Map<String, Menu> menuMap;
    private final AtomicInteger atomicInteger;

    public InMemoryMenuRepository() {
        this.menuMap = new TreeMap<>();
        this.atomicInteger = new AtomicInteger();
    }

    @Override
    public String create(Menu menu) {
        menu.setId(String.format("material%d", atomicInteger.incrementAndGet()));
        menuMap.put(menu.getId(), menu);
        return menu.getId();
    }

    @Override
    public void update(Menu menu) throws MenuNotFoundException {
        if (!menuMap.containsKey(menu.getId())) {
            throw new MenuNotFoundException();
        }
        menuMap.put(menu.getId(), menu);
    }

    @Override
    public void delete(String id) throws MenuNotFoundException {
        if (!menuMap.containsKey(id)) {
            throw new MenuNotFoundException();
        }
        menuMap.remove(id);
    }

    @Override
    public Menu findById(String id) throws MenuNotFoundException {
        if (!menuMap.containsKey(id)) {
            throw new MenuNotFoundException();
        }
        return menuMap.get(id);
    }

    @Override
    public List<Menu> findAll() {
        return menuMap.values().stream().toList();
    }
}
