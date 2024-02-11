package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.InvalidPurchaseException;
import io.micrometer.common.util.StringUtils;

public record PurchaseItem(
        String materialId,
        double price,
        double amount
) {

    public PurchaseItem {
        validateMaterialId(materialId);
        validatePrice(price);
        validateAmount(amount);
    }

    private void validateMaterialId(String materialId) {
        if (StringUtils.isBlank(materialId)) {
            throw new InvalidPurchaseException();
        }
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new InvalidPurchaseException();
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidPurchaseException();
        }
    }

    public double getUnitPrice() {
        return price / amount;
    }

}
