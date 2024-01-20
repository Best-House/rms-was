package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPurchaseRepositoryTest {

    InMemoryPurchaseRepository purchaseRepository;

    @BeforeEach
    void setUp() {
        purchaseRepository = new InMemoryPurchaseRepository();
    }

    @Test
    void findRecentPurchaseItemsBy() {
        long firstPurchaseDate = 100;
        long secondPurchaseDate = 200;
        long thirdPurchaseDate = 300;
        Purchase purchase1 = new Purchase();
        purchase1.setId("purchase1");
        purchase1.setPurchaseItems(List.of(
                new PurchaseItem("material1", 100, 10, firstPurchaseDate),
                new PurchaseItem("material2", 100, 10, firstPurchaseDate))
        );
        Purchase purchase2 = new Purchase();
        purchase2.setId("purchase2");
        purchase2.setPurchaseItems(List.of(
                new PurchaseItem("material2", 200, 10, secondPurchaseDate),
                new PurchaseItem("material3", 100, 10, secondPurchaseDate))
        );
        Purchase purchase3 = new Purchase();
        purchase3.setId("purchase3");
        purchase3.setPurchaseItems(List.of(
                new PurchaseItem("material1", 500, 10, thirdPurchaseDate),
                new PurchaseItem("material3", 100, 10, thirdPurchaseDate))
        );

        purchaseRepository.create(purchase1);
        purchaseRepository.create(purchase2);
        purchaseRepository.create(purchase3);

        List<PurchaseItem> purchaseItems = purchaseRepository.findRecentPurchaseItemsBy(List.of("material1", "material2"));

        Map<String, PurchaseItem> result = purchaseItems.stream()
                .collect(Collectors.toMap(PurchaseItem::materialId, Function.identity()));
        assertEquals(thirdPurchaseDate, result.get("material1").purchaseDate());
        assertEquals(secondPurchaseDate, result.get("material2").purchaseDate());
        assertEquals(500, result.get("material1").price());
        assertEquals(200, result.get("material2").price());
    }
}
