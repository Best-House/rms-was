package com.bh.rms.domain.aggregate.purchase.service;

import com.bh.rms.domain.aggregate.purchase.Purchase;
import com.bh.rms.domain.aggregate.purchase.PurchaseFactory;
import com.bh.rms.domain.aggregate.purchase.PurchaseItem;
import com.bh.rms.domain.aggregate.purchase.exception.PurchaseNotFoundException;
import com.bh.rms.domain.aggregate.purchase.infra.PurchaseRepository;
import com.bh.rms.web.dto.PurchaseCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    PurchaseRepository purchaseRepository;

    @InjectMocks
    PurchaseService purchaseService;

    @Test
    void create() {
        String expect = "purchase1";
        when(purchaseRepository.create(any())).thenReturn(expect);

        String result = purchaseService.create(any());

        assertEquals(result, expect);
    }

    @Test
    void delete() {
        String purchaseId = "purchase1";

        purchaseService.delete(purchaseId);

        verify(purchaseRepository).delete(purchaseId);
    }

    @Test
    void deleteWithNotExistPurchase() {
        String purchaseId = "purchase1";
        doThrow(PurchaseNotFoundException.class)
                .when(purchaseRepository)
                .delete(purchaseId);

        assertThrows(PurchaseNotFoundException.class, () ->
                purchaseService.delete(purchaseId));

        verify(purchaseRepository).delete(purchaseId);
    }
}
