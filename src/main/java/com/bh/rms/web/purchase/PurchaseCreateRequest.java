package com.bh.rms.web.purchase;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseFactory;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseCreateRequest {

    @Valid
    @NotEmpty
    private List<PurchaseItemInput> purchaseItems;

    @Data
    public static class PurchaseItemInput {
        @NotBlank
        private String materialId;
        @Min(0)
        private Double price;
        @Positive
        private Double amount;
    }

    public Purchase makePurchaseForCreate() {
        long purchaseDate = System.currentTimeMillis();
        List<PurchaseItem> purchaseItemList = purchaseItems.stream()
                .map(itemDto -> new PurchaseItem(
                        itemDto.materialId,
                        itemDto.price,
                        itemDto.amount,
                        purchaseDate
                )).toList();
        return PurchaseFactory.forCreate()
                .setPurchaseItems(purchaseItemList)
                .build();
    }
}
