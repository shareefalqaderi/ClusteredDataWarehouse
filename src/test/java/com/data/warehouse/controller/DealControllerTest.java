package com.data.warehouse.controller;

import com.data.warehouse.model.Deal;
import com.data.warehouse.service.DealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @Test
    void createDeal_Success() {
        Deal deal = new Deal(1L, "JOD", "USD", "2024-06-13 08:31:00", "1000");
        when(dealService.createDeal(deal)).thenReturn(deal);

        ResponseEntity<?> response = dealController.createDeal(deal);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(dealService).createDeal(deal);
    }
}