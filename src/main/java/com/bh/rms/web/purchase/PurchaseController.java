package com.bh.rms.web.purchase;

import com.bh.rms.controller.AbstractApiController;
import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseService;
import com.bh.rms.web.purchase.PurchaseCreateRequest;
import com.bh.rms.web.purchase.PurchaseCreateResponse;
import com.bh.rms.web.purchase.PurchaseUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public PurchaseCreateResponse create(@RequestBody @Valid PurchaseCreateRequest request) {
        return new PurchaseCreateResponse(purchaseService.create(request.makePurchaseForCreate()));
    }

    @DeleteMapping("/purchases/{purchaseId}")
    public void delete(@PathVariable String purchaseId) {
        purchaseService.delete(purchaseId);
    }

    @PutMapping("/purchases/{purchaseId}")
    public void update(@PathVariable String purchaseId, @RequestBody @Valid PurchaseUpdateRequest request) {
        purchaseService.update(request.makePurchaseForUpdate(purchaseId));
    }

    @GetMapping("/purchases/{purchaseId}")
    public Purchase find(@PathVariable String purchaseId) {
        return purchaseService.find(purchaseId);
    }

    @GetMapping("/purchases")
    public List<Purchase> findAll() {
        return purchaseService.findAll();
    }

}
