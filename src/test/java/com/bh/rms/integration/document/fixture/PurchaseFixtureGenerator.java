package com.bh.rms.integration.document.fixture;

import com.bh.rms.web.purchase.PurchaseController;
import com.bh.rms.web.purchase.PurchaseCreateRequest;
import com.bh.rms.web.purchase.PurchaseUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class PurchaseFixtureGenerator {
    private final PurchaseController purchaseController;
    private final MaterialFixtureGenerator materialFixtureGenerator;
    private Set<String> purchaseIdSet;

    public PurchaseFixtureGenerator(PurchaseController purchaseController, MaterialFixtureGenerator materialFixtureGenerator) {
        this.purchaseController = purchaseController;
        this.materialFixtureGenerator = materialFixtureGenerator;
        purchaseIdSet = new HashSet<>();
    }

    public String createPurchase() {
        String purchaseId = purchaseController.create(getPurchaseCreateRequest()).getId();
        purchaseIdSet.add(purchaseId);
        return purchaseId;
    }

    public PurchaseCreateRequest getPurchaseCreateRequest() {
        PurchaseCreateRequest purchaseCreateRequest = new PurchaseCreateRequest();
        List<PurchaseCreateRequest.PurchaseItemDto> purchaseItems = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            var purchaseItem = new PurchaseCreateRequest.PurchaseItemDto();
            String materialId = materialFixtureGenerator.createMaterial();
            purchaseItem.setMaterialId(materialId);
            purchaseItem.setPrice((double) i);
            purchaseItem.setAmount((double) i);
            purchaseItems.add(purchaseItem);
        }
        purchaseCreateRequest.setPurchaseItems(purchaseItems);
        return purchaseCreateRequest;
    }

    public PurchaseUpdateRequest getPurchaseUpdateRequest() {
        PurchaseUpdateRequest purchaseUpdateRequest = new PurchaseUpdateRequest();
        List<PurchaseUpdateRequest.PurchaseItemDto> purchaseItems = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            var purchaseItem = new PurchaseUpdateRequest.PurchaseItemDto();
            String materialId = materialFixtureGenerator.createMaterial();
            purchaseItem.setMaterialId(materialId);
            purchaseItem.setPrice((double) i);
            purchaseItem.setAmount((double) i);
            purchaseItem.setPurchaseDate(System.currentTimeMillis());
            purchaseItems.add(purchaseItem);
        }
        purchaseUpdateRequest.setPurchaseItems(purchaseItems);
        return purchaseUpdateRequest;
    }

    public void cleanUp() {
        for(String purchaseId : purchaseIdSet) {
            deletePurchase(purchaseId);
        }
        purchaseIdSet = new HashSet<>();
    }


    public void deletePurchase(String purchaseId) {
        try {
            purchaseController.delete(purchaseId);
        } catch (Exception e) {
            log.warn("Delete purchase failed. purchaseId: " + purchaseId + " ,message: " + e.getMessage());
        }
    }
}
