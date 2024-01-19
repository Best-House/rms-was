package com.bh.rms.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.PurchaseRepository;
import com.bh.rms.domain.aggregate.recipe.exception.NotFoundRecipeException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Map<String, PurchaseItem> recentPurchaseItems = new HashMap<>();
        for (Purchase purchase : purchaseMap.values()) {
            Map<String, PurchaseItem> filteredMaterialIdPurchaseItems = purchase.getPurchaseItems().stream()
                    .filter(purchaseItem -> materialIds.contains(purchaseItem.getMaterialId()))
                    .collect(Collectors.toMap(PurchaseItem::getMaterialId, Function.identity()));

            recentPurchaseItems = Stream.of(recentPurchaseItems, filteredMaterialIdPurchaseItems)
                    .flatMap(map -> map.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldVal, newVal) -> oldVal.getPurchaseDate() > newVal.getPurchaseDate() ? oldVal : newVal));
        }
        return recentPurchaseItems.values()
                .stream()
                .toList();
    }
    @Override
    public String create(Purchase purchase) {
        purchase.setId(String.format("purchase%d", atomicInteger.incrementAndGet()));
        purchaseMap.put(purchase.getId(), purchase);
        return purchase.getId();
    }

    @Override
    public void update(Purchase purchase) {
        if(!purchaseMap.containsKey(purchase.getId())) {
            throw new NotFoundRecipeException();
        }
        purchaseMap.put(purchase.getId(), purchase);
    }

    @Override
    public void delete(String purchaseId) {
        if(!purchaseMap.containsKey(purchaseId)) {
            throw new NotFoundRecipeException();
        }
        purchaseMap.remove(purchaseId);
    }

    @Override
    public Purchase findById(String purchaseId) throws NotFoundRecipeException {
        if(!purchaseMap.containsKey(purchaseId)) {
            throw new NotFoundRecipeException();
        }
        return purchaseMap.get(purchaseId);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseMap.values().stream().toList();
    }
}
