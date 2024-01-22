package com.bh.rms.domain.aggregate.purchase;

import com.bh.rms.domain.aggregate.purchase.exception.InvalidPurchaseException;
import com.bh.rms.domain.exception.InvalidAggregateIdException;
import io.micrometer.common.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Purchase {

    private String id;

    private List<PurchaseItem> purchaseItems;

    public Purchase() {}

    public void setId(String id) {
        if (StringUtils.isBlank(id)) {
            throw new InvalidAggregateIdException();
        }
        this.id = id;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        if (Objects.isNull(purchaseItems) || purchaseItems.isEmpty()) {
            throw new InvalidPurchaseException();
        }
        this.purchaseItems = purchaseItems;
    }

    public List<String> getContainedMaterialIds() {
        return purchaseItems.stream()
                .map(PurchaseItem::materialId)
                .toList();
    }
}
