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
public class PurchaseUpdateRequest {

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
        @Positive
        private Long purchaseDate;
    }

    public Purchase makePurchaseForUpdate(String purchaseId) {
        List<PurchaseItem> purchaseItemList = purchaseItems.stream()
                .map(input -> new PurchaseItem(
                        input.materialId,
                        input.price,
                        input.amount
                )).toList();
        return PurchaseFactory.forUpdate()
                .setId(purchaseId)
                .setPurchaseItems(purchaseItemList)
                .build();
    }
}
