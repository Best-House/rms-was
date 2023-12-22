package com.bh.rms.domain.aggregate.purchase.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PurchaseUpdateRequest {

    @NotBlank
    private String materialId;
    @Min(0)
    private double price;
    @Positive
    private double amount;
    @Positive
    private long purchaseDate;
}
