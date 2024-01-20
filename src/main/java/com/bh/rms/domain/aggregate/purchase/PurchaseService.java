package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.material.MaterialService;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final MaterialService materialService;

    public PurchaseService(PurchaseRepository purchaseRepository, MaterialService materialService) {
        this.purchaseRepository = purchaseRepository;
        this.materialService = materialService;
    }

    public String create(Purchase purchase) {
        validateExistMaterialIds(purchase);
        return purchaseRepository.create(purchase);
    }

    public void update(Purchase purchase) {
        validateExistMaterialIds(purchase);
        purchaseRepository.update(purchase);
    }

    private void validateExistMaterialIds(Purchase purchase) {
        List<String> materialIds = purchase.getPurchaseItems().stream()
                .map(PurchaseItem::materialId)
                .toList();
        if (!materialService.existByIds(materialIds)) {
            throw new MaterialNotFoundException();
        }
    }

    public void delete(String purchaseId) {
        purchaseRepository.delete(purchaseId);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase find(String purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }

}
