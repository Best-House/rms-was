package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPurchaseRepositoryTest {

    InMemoryPurchaseRepository purchaseRepository;

    @BeforeEach
    void setUp() {
        purchaseRepository = new InMemoryPurchaseRepository();
    }

    @Test
    void findRecentByMaterialIds() {
        long earlyCreateTime = System.currentTimeMillis();
        long laterCreateTime = System.currentTimeMillis();
        purchaseRepository.createBulk(List.of(
                new Purchase("material1", 100, 10, earlyCreateTime),
                new Purchase("material2", 100, 10, earlyCreateTime),
                new Purchase("material2", 200, 10, laterCreateTime)
        ));

        List<Purchase> recentPurchases = purchaseRepository.findRecentByMaterialIds(List.of("material1", "material2"));

        assertEquals(earlyCreateTime, recentPurchases.get(0).getPurchaseDate());
        assertEquals(laterCreateTime, recentPurchases.get(1).getPurchaseDate());
        assertEquals(200, recentPurchases.get(1).getPrice());
    }
}
