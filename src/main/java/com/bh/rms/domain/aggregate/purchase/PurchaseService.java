package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialFactory;
import com.bh.rms.domain.aggregate.material.MaterialService;
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
        changeUnknownMaterialIds(purchase);
        return purchaseRepository.create(purchase);
    }

    public void update(Purchase purchase) {
        changeUnknownMaterialIds(purchase);
        purchaseRepository.update(purchase);
    }

    private void changeUnknownMaterialIds(Purchase purchase) {
        purchase.getPurchaseItems().stream()
                .filter(purchaseItem -> !materialService.existById(purchaseItem.getMaterialId()))
                .forEach(purchaseItem -> {
                    String materialId = createUnknownMaterial(purchaseItem);
                    purchaseItem.setMaterialId(materialId);
                });
    }

    private String createUnknownMaterial(PurchaseItem purchaseItem) {
        Material material = MaterialFactory.forCreate()
                .setUnknownName(purchaseItem.getMaterialId())
                .setDefaultUnitPrice(null)
                .build();
        return materialService.create(material.getName(), material.getDefaultUnitPrice());
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
