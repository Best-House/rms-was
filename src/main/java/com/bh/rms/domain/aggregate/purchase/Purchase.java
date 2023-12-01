package com.bh.rms.domain.aggregate.purchase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Purchase {

    private String id;
    private String materialId;
//    private long number;
    private double price;
    private double amount;
//    private long purchaseDate;

    public Purchase(String id, String materialId, double price, double amount) {
        this.id = id;
        this.materialId = materialId;
//        this.number = number;
        this.price = price;
        this.amount = amount;
//        this.purchaseDate = System.currentTimeMillis();
    }

    public void setId(String format) {

    }

    public double getUnitPrice() {
        return price / amount;
    }

}
