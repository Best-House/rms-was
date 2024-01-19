package com.bh.rms.domain.aggregate.purchase.service;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public String create(Purchase purchase) {
        return purchaseRepository.create(purchase);
    }

    public void delete(String purchaseId) {
        purchaseRepository.delete(purchaseId);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public void update(Purchase purchase) {
        purchaseRepository.update(purchase);
    }
}
