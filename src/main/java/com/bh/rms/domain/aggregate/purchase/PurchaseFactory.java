package com.bh.rms.domain.aggregate.purchase;

import java.util.List;

public class PurchaseFactory {

    public static PurchaseFactoryForCreate forCreate() {
        return new PurchaseFactoryForCreate();
    }

    public static PurchaseFactoryForUpdate forUpdate() {
        return new PurchaseFactoryForUpdate();
    }

    public static class PurchaseFactoryForCreate {

        private List<PurchaseItem> purchaseItems;

        public Purchase build() {
            Purchase purchase = new Purchase();
            purchase.setCreatedDate(System.currentTimeMillis());
            purchase.setPurchaseItems(purchaseItems);
            return purchase;
        }

        public PurchaseFactoryForCreate setPurchaseItems(List<PurchaseItem> purchaseItems) {
            this.purchaseItems = purchaseItems;
            return this;
        }

    }

    public static class PurchaseFactoryForUpdate {

        private String id;
        private List<PurchaseItem> purchaseItems;

        public Purchase build() {
            Purchase purchase = new Purchase();
            purchase.setId(id);
            purchase.setCreatedDate(System.currentTimeMillis());
            purchase.setPurchaseItems(purchaseItems);
            return purchase;
        }

        public PurchaseFactoryForUpdate setId(String id) {
            this.id = id;
            return this;
        }

        public PurchaseFactoryForUpdate setPurchaseItems(List<PurchaseItem> purchaseItems) {
            this.purchaseItems = purchaseItems;
            return this;
        }

    }

}
