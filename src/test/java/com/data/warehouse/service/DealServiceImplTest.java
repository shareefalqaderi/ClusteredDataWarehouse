package com.data.warehouse.service;

import com.data.warehouse.exception.CreateDealException;
import com.data.warehouse.model.Deal;
import com.data.warehouse.repository.DealRepository;
import com.data.warehouse.service.impl.DealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    private DealService dealService;

    private Deal deal;

    @BeforeEach
    void setUp() {
        dealService = new DealServiceImpl(dealRepository);
    }

    @Test
    void createDeal() {
        deal = new Deal(1L, "JOD", "USD", "2024-06-13 08:31:00", "1000");

        when(dealRepository.save(deal)).thenReturn(deal);
        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertEquals(deal, dealService.createDeal(deal));

        verify(dealRepository).save(deal);
        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealId_IsRequired() {
        deal = new Deal(null, "JOD", "USD", "2024-06-13 08:31:00", "1000");

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));
    }

    @Test
    void validateDealId_Duplicate() {
        deal = new Deal(1L, "JOD", "USD", "2024-06-13 08:31:00", "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.of(deal));

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealFromCurrencyCode_IsRequired() {
        deal = new Deal(1L, null, "USD", "2024-06-13 08:31:00", "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealFromCurrencyCode_IncorrectFormat() {
        deal = new Deal(1L, "JOR", "USD", "2024-06-13 08:31:00", "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealToCurrencyCode_IsRequired() {
        deal = new Deal(1L, "JOD", null, "2024-06-13 08:31:00", "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealToCurrencyCode_IncorrectFormat() {
        deal = new Deal(1L, "JOD", "USA", "2024-06-13 08:31:00", "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealTimestamp_IsRequired() {
        deal = new Deal(1L, "JOD", "USD", null, "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealTimestamp_IncorrectFormat() {
        deal = new Deal(1L, "JOD", "USD", "2024-06-13 08;31:00", "1000");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealAmount_IsRequired() {
        deal = new Deal(1L, "JOD", "USD", "2024-06-13 08:31:00", null);

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }

    @Test
    void validateDealAmount_IncorrectFormat() {
        deal = new Deal(1L, "JOD", "USD", "2024-06-13 08:31:00", "ABC");

        when(dealRepository.findById(deal.getId())).thenReturn(Optional.empty());

        assertThrows(CreateDealException.class, () -> dealService.createDeal(deal));

        verify(dealRepository).findById(deal.getId());
    }
}