package com.bh.rms.domain.aggregate.purchase.service;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.web.dto.PurchaseCreateRequest;
import com.bh.rms.web.dto.PurchaseUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<String> create(PurchaseCreateRequest request) {
        final long purchaseDate = System.currentTimeMillis();
        final List<Purchase> purchases = request.getPurchaseItems().stream()
                .map(purchaseItem -> new Purchase(
                        purchaseItem.getMaterialId(),
                        purchaseItem.getPrice(),
                        purchaseItem.getAmount(),
                        purchaseDate)
                ).toList();
        return purchaseRepository.createBulk(purchases);
    }

    public void delete(String purchaseId) {
        purchaseRepository.delete(purchaseId);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public void update(String purchaseId, PurchaseUpdateRequest request) {
        Purchase purchase = purchaseRepository.findById(purchaseId);
        purchase.update(request.getMaterialId(), request.getPrice(), request.getAmount(), request.getPurchaseDate());
        purchaseRepository.update(purchase);
    }
}
