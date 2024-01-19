package com.bh.rms.domain.aggregate.purchase.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.exception.PurchaseNotFoundException;

import java.util.List;
import java.util.Map;

public interface PurchaseRepository {

    String create(Purchase purchase);
    void update(Purchase purchase) throws PurchaseNotFoundException;
    void delete(String purchaseId) throws PurchaseNotFoundException;
    Purchase findById(String purchaseId) throws PurchaseNotFoundException;
    List<Purchase> findAll();
    List<PurchaseItem> findRecentPurchaseItemsBy(List<String> materialIds);
}
