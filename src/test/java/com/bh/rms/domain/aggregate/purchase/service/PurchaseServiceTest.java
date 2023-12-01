package com.bh.rms.domain.aggregate.purchase.service;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.domain.aggregate.purchase.service.dto.PurchaseCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    PurchaseRepository purchaseRepository;

    @InjectMocks
    PurchaseService purchaseService;

    @Test
    void create() {
        PurchaseCreateRequest request = new PurchaseCreateRequest(List.of(
                new PurchaseCreateRequest.PurchaseItem("material1", 100, 1),
                new PurchaseCreateRequest.PurchaseItem("material2", 100, 1),
                new PurchaseCreateRequest.PurchaseItem("material3", 100, 1)
        ));
        List<String> purchaseIds = List.of("purchase1", "purchase2", "purchase3");
        when(purchaseRepository.createBulk(anyList())).thenReturn(purchaseIds);

        List<String> result = purchaseService.create(request);

        assertEquals(result, purchaseIds);
    }

}
