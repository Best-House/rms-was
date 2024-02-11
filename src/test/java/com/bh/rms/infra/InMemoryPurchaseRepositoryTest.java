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
        purchase1.setCreatedDate(firstPurchaseDate);
        purchase1.setPurchaseItems(List.of(
                new PurchaseItem("material1", 100, 10),
                new PurchaseItem("material2", 200, 10))
        );
        Purchase purchase2 = new Purchase();
        purchase2.setId("purchase2");
        purchase2.setCreatedDate(secondPurchaseDate);
        purchase2.setPurchaseItems(List.of(
                new PurchaseItem("material2", 300, 10),
                new PurchaseItem("material3", 400, 10))
        );
        Purchase purchase3 = new Purchase();
        purchase3.setId("purchase3");
        purchase3.setCreatedDate(thirdPurchaseDate);
        purchase3.setPurchaseItems(List.of(
                new PurchaseItem("material1", 500, 10),
                new PurchaseItem("material3", 600, 10))
        );

        purchaseRepository.create(purchase1);
        purchaseRepository.create(purchase2);
        purchaseRepository.create(purchase3);

        List<PurchaseItem> purchaseItems = purchaseRepository.findRecentPurchaseItemsBy(List.of("material1", "material2"));


        assertEquals(2, purchaseItems.size());
        assertTrue(purchaseItems.stream().anyMatch(pi -> pi.materialId().equals("material1")));
        assertTrue(purchaseItems.stream().anyMatch(pi -> pi.materialId().equals("material2")));
        assertTrue(purchaseItems.stream().noneMatch(pi -> pi.materialId().equals("material3")));

        assertEquals(500, purchaseItems.stream().filter(pi -> pi.materialId().equals("material1")).findFirst().orElse(null).price());
        assertEquals(300, purchaseItems.stream().filter(pi -> pi.materialId().equals("material2")).findFirst().orElse(null).price());
    }
}
