package com.bh.rms.web.dto;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseFactory;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PurchaseUpdateRequest {

    @Valid
    @NotEmpty
    private List<PurchaseItemUpdateDto> purchaseItems;

    @AllArgsConstructor
    @Data
    public static class PurchaseItemUpdateDto {
        @NotBlank
        private String materialId;
        @Min(0)
        private Double price;
        @Positive
        private Double amount;
        @Positive
        private Long purchaseDate;
    }

    public Purchase toEntity(String purchaseId) {
        List<PurchaseItem> purchaseItemList = purchaseItems.stream()
                .map(purchaseItemDto -> new PurchaseItem(
                        purchaseItemDto.materialId,
                        purchaseItemDto.price,
                        purchaseItemDto.amount,
                        purchaseItemDto.purchaseDate
                )).toList();
        return PurchaseFactory.forUpdate()
                .setId(purchaseId)
                .setPurchaseItems(purchaseItemList)
                .build();
    }
}
