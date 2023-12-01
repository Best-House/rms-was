package com.bh.rms.controller;

import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.domain.aggregate.purchase.service.PurchaseService;
import com.bh.rms.domain.aggregate.purchase.service.dto.PurchaseCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public List<String> create(@RequestBody @Valid PurchaseCreateRequest request) {
        return purchaseService.create(request);
    }
}
