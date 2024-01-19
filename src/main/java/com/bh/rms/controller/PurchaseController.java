package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseService;
import com.bh.rms.web.purchase.PurchaseCreateRequest;
import com.bh.rms.web.purchase.PurchaseUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController extends AbstractApiController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchases")
    public String create(@RequestBody @Valid PurchaseCreateRequest request) {
        return purchaseService.create(request.makePurchaseForCreate());
    }

    @DeleteMapping("/purchases/{purchaseId}")
    public void delete(@PathVariable String purchaseId) {
        purchaseService.delete(purchaseId);
    }

    @GetMapping("/purchases")
    public List<Purchase> findAll() {
        return purchaseService.findAll();
    }

    @PostMapping("/purchases/{purchaseId}")
    public void update(@PathVariable String purchaseId, @RequestBody @Valid PurchaseUpdateRequest request) {
        purchaseService.update(request.makePurchaseForUpdate(purchaseId));
    }
}
