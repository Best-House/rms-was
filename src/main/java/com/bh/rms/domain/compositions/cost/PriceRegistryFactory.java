package com.bh.rms.domain.compositions.cost;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialRepository;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.PurchaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceRegistryFactory {
    private final MaterialRepository materialRepository;
    private final PurchaseRepository purchaseRepository;

    public PriceRegistryFactory(MaterialRepository materialRepository, PurchaseRepository purchaseRepository) {
        this.materialRepository = materialRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public PriceRegistry compounded(List<String> materialIds) {
        List<Material> materials = materialRepository.findByIds(materialIds);
        List<PurchaseItem> purchaseItems = purchaseRepository.findRecentPurchaseItemsBy(materialIds);

        CompoundPriceRegistry priceRegistry = new CompoundPriceRegistry();
        priceRegistry.putDefaultUnitPriceOf(materials);
        priceRegistry.putPurchaseUnitPrice(purchaseItems);
        return priceRegistry;
    }
}
