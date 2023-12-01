package com.bh.rms.domain.aggregate.purchase.infra;

import com.bh.rms.domain.aggregate.purchase.Purchase;

import java.util.List;

public interface PurchaseRepository {
    List<String> createBulk(List<Purchase> purchase);
//
//    void update(Purchase purchase) throws PurchaseNotFoundException;
//
//    void delete(String purchaseId) throws PurchaseNotFoundException;
//
//    Purchase findById(String purchaseId) throws PurchaseNotFoundException;
//
//    List<Purchase> findByIds(List<String> purchaseIds);
//
//    List<Purchase> findAll();

    List<Purchase> findRecentByMaterialIds(List<String> materialIdsOfIngredients);

}
