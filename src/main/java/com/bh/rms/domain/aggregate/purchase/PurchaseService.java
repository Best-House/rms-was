package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
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
        validateNotExistMaterialIds(purchase);
        return purchaseRepository.create(purchase);
    }

    private void validateNotExistMaterialIds(Purchase purchase) {
        List<String> materialIds = purchase.getPurchaseItems().stream()
                .map(PurchaseItem::materialId)
                .toList();
        if (!materialService.existByIds(materialIds)) {
            throw new MaterialNotFoundException();
        }
    }

    public void update(Purchase purchase) {
        validateNotExistMaterialIds(purchase);
        purchaseRepository.update(purchase);
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
