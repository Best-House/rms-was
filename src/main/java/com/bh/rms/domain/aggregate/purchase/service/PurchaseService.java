package com.bh.rms.domain.aggregate.purchase.service;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.domain.aggregate.purchase.service.dto.PurchaseCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<String> create(PurchaseCreateRequest request) {
        List<Purchase> purchases = request.getPurchaseItems().stream()
                .map(purchaseItem -> new Purchase(
                        purchaseItem.getMaterialId(),
                        purchaseItem.getPrice(),
                        purchaseItem.getAmount())
                ).toList();
        return purchaseRepository.createBulk(purchases);
    }

    public void delete(String purchaseId) {
        purchaseRepository.delete(purchaseId);
    }
}
