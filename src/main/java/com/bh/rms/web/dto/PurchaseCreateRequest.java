package com.bh.rms.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PurchaseCreateRequest {

    @Valid
    private List<PurchaseItem> purchaseItems;

    @AllArgsConstructor
    @Data
    public static class PurchaseItem {
        @NotBlank
        private String materialId;
        @Min(0)
        private double price;
        @Positive
        private double amount;
    }
}
