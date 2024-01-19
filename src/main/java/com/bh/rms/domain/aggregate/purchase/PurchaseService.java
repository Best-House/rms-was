package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import com.bh.rms.domain.aggregate.material.MaterialRepository;
import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final MaterialRepository materialRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, MaterialRepository materialRepository) {
        this.purchaseRepository = purchaseRepository;
        this.materialRepository = materialRepository;
    }

    public String create(Purchase purchase) {
        validateExistMaterialIds(purchase);
        return purchaseRepository.create(purchase);
    }

    private void validateExistMaterialIds(Purchase purchase) {
        // TODO 없는 materialId면 예외가 발생하는게 맞을까?
        List<String> materialIds = purchase.getPurchaseItems().stream()
                .map(PurchaseItem::getMaterialId)
                .toList();
        if (!materialRepository.existByIds(materialIds)) {
            throw new MaterialNotFoundException();
        }
    }

    public void delete(String purchaseId) {
        purchaseRepository.delete(purchaseId);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public void update(Purchase purchase) {
        validateExistMaterialIds(purchase);
        purchaseRepository.update(purchase);
    }

    public Purchase find(String purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }
}
