package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.PurchaseNotFoundException;

import java.util.List;

public interface PurchaseRepository {

    String create(Purchase purchase);
    void update(Purchase purchase) throws PurchaseNotFoundException;
    void delete(String purchaseId) throws PurchaseNotFoundException;
    Purchase findById(String purchaseId) throws PurchaseNotFoundException;
    List<Purchase> findAll();
    List<PurchaseItem> findRecentPurchaseItemsBy(List<String> materialIds);
}
