package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.domain.aggregate.recipe.Recipe;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Primary
public class InMemoryPurchaseRepository implements PurchaseRepository {
    private final Map<String, Purchase> purchaseMap;
    private final AtomicInteger atomicInteger;

    public InMemoryPurchaseRepository() {
        purchaseMap = new HashMap<>();
        atomicInteger = new AtomicInteger();
    }

    @Override
    public List<Purchase> findRecentByMaterialIds(List<String> materialIds) {
        List<Purchase> recentPurchases = new ArrayList<>();
        for (String materialId : materialIds) {
            List<Purchase> purchasesWithMaterialId = purchaseMap.values().stream()
                    .filter(purchase -> purchase.getMaterialId().equals(materialId))
                    .toList();
            Purchase recentPurchase = Collections.max(
                    purchasesWithMaterialId,
                    Comparator.comparing(Purchase::getPurchaseDate)
            );
            recentPurchases.add(recentPurchase);
        }
        return recentPurchases;
    }

    @Override
    public List<String> createBulk(List<Purchase> purchases) {
        List<String> purchaseIds = new ArrayList<>();

        for(Purchase purchase : purchases) {
            purchase.setId(String.format("purchase%d", atomicInteger.incrementAndGet()));
            purchaseIds.add(purchase.getId());
            purchaseMap.put(purchase.getId(), purchase);
        }
        return purchaseIds;
    }
//
//    @Override
//    public void update(Purchase purchase) {
//        if(!purchaseMap.containsKey(purchase.getId())) {
//            throw new NotFoundRecipeException();
//        }
//        purchaseMap.put(purchase.getId(), purchase);
//    }
//
    @Override
    public void delete(String purchaseId) {
        if(!purchaseMap.containsKey(purchaseId)) {
            throw new NotFoundRecipeException();
        }
        purchaseMap.remove(purchaseId);
    }
//
//    @Override
//    public Purchase findById(String purchaseId) throws NotFoundRecipeException {
//        if(!purchaseMap.containsKey(purchaseId)) {
//            throw new NotFoundRecipeException();
//        }
//        return purchaseMap.get(purchaseId);
//    }

    @Override
    public List<Purchase> findAll() {
        return purchaseMap.values().stream().toList();
    }
}
