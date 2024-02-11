package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.PurchaseRepository;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;
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
    public List<PurchaseItem> findRecentPurchaseItemsBy(List<String> materialIds) {
        List<String> targetsMaterialIds = new ArrayList<>(materialIds);
        List<Purchase> sortedPurchases = purchaseMap.values()
                .stream().sorted(Comparator.comparing(Purchase::getCreatedDate).reversed())
                .toList();

        List<PurchaseItem> result = new ArrayList<>();
        for(Purchase purchase : sortedPurchases) {
            for(int i = targetsMaterialIds.size() - 1; i >= 0; i--) {
                String materialId = targetsMaterialIds.get(i);
                if(purchase.contains(materialId)) {
                    result.add(purchase.getPurchaseItemOf(materialId));
                    targetsMaterialIds.remove(i);
                }
            }
        }
        return result;
    }

    @Override
    public String create(Purchase purchase) {
        purchase.setId(String.format("purchase%d", atomicInteger.incrementAndGet()));
        purchaseMap.put(purchase.getId(), purchase);
        return purchase.getId();
    }

    @Override
    public void update(Purchase purchase) {
        if (!purchaseMap.containsKey(purchase.getId())) {
            throw new NotFoundRecipeException();
        }
        purchaseMap.put(purchase.getId(), purchase);
    }

    @Override
    public void delete(String purchaseId) {
        if (!purchaseMap.containsKey(purchaseId)) {
            throw new NotFoundRecipeException();
        }
        purchaseMap.remove(purchaseId);
    }

    @Override
    public Purchase findById(String purchaseId) throws NotFoundRecipeException {
        if (!purchaseMap.containsKey(purchaseId)) {
            throw new NotFoundRecipeException();
        }
        return purchaseMap.get(purchaseId);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseMap.values().stream().toList();
    }
}
