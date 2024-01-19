package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.InvalidPurchaseException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import io.micrometer.common.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class PurchaseItem {

    private String materialId;
//    private long number;
    private double price;
    private double amount;
    private long purchaseDate;

    public PurchaseItem() {}

    public PurchaseItem(String materialId, double price, double amount, long purchaseDate) {
        setMaterialId(materialId);
        setPrice(price);
        setAmount(amount);
        setPurchaseDate(purchaseDate);
    }

    public void setMaterialId(String materialId) {
        if (StringUtils.isBlank(materialId)) {
            throw new InvalidPurchaseException();
        }
        this.materialId = materialId;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidPurchaseException();
        }
        this.price = price;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidPurchaseException();
        }
        this.amount = amount;
    }

    public void setPurchaseDate(long purchaseDate) {
        //TODO
        //any validation?
        this.purchaseDate = purchaseDate;
    }

    public double getUnitPrice() {
        return price / amount;
    }

    public void update(String materialId, double price, double amount, long purchaseDate) {
        setMaterialId(materialId);
        setPrice(price);
        setAmount(amount);
        setPurchaseDate(purchaseDate);
    }
}
