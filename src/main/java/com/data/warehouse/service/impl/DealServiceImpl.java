package com.data.warehouse.service.impl;

import com.data.warehouse.exception.CreateDealException;
import com.data.warehouse.model.Deal;
import com.data.warehouse.repository.DealRepository;
import com.data.warehouse.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

@Service
public class DealServiceImpl implements DealService {

    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    DealRepository dealRepository;

    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public Deal createDeal(Deal deal) throws CreateDealException {

        validateDealId(deal);

        validateDealFromCurrencyCode(deal);

        validateDealToCurrencyCode(deal);

        validateDealTimestamp(deal);

        validateDealAmount(deal);

        logger.info("Saving the deal");
        return dealRepository.save(deal);
    }

    private void validateDealId(Deal deal) throws CreateDealException {
        logger.info("Validating Deal ID");
        if (deal.getId() == null) {
            throw new CreateDealException("Deal Unique Id is Required");
        }
        if (dealRepository.findById(deal.getId()).isPresent()) {
            throw new CreateDealException("Deal Id is Duplicated");
        }
    }

    private void validateDealFromCurrencyCode(Deal deal) throws CreateDealException {
        logger.info("Validating Deal Currency ISO Code (From)");
        if (deal.getFromCurrencyCode() == null || deal.getFromCurrencyCode().isEmpty()) {
            throw new CreateDealException("Deal Currency ISO Code (From) is Required");
        }
        try {
            Currency.getInstance(deal.getFromCurrencyCode());
        } catch (IllegalArgumentException e) {
            throw new CreateDealException("Deal Currency ISO Code (From) is Invalid");
        }
    }

    private void validateDealToCurrencyCode(Deal deal) throws CreateDealException {
        logger.info("Validating Deal Currency ISO Code (To)");
        if (deal.getToCurrencyCode() == null || deal.getToCurrencyCode().isEmpty()) {
            throw new CreateDealException("Deal Currency ISO Code (To) is Required");
        }
        try {
            Currency.getInstance(deal.getToCurrencyCode());
        } catch (IllegalArgumentException e) {
            throw new CreateDealException("Deal Currency ISO Code (To) is Invalid");
        }
    }

    private void validateDealTimestamp(Deal deal) throws CreateDealException {
        logger.info("Validating Deal Timestamp");
        if (deal.getTimestamp() == null || deal.getTimestamp().isEmpty()) {
            throw new CreateDealException("Deal Timestamp is Required");
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(deal.getTimestamp());
            if (date.after(new Date())) {
                throw new CreateDealException("Deal Timestamp Format is Invalid");
            }
        } catch (ParseException e) {
            throw new CreateDealException(e.getMessage());
        }
    }

    private void validateDealAmount(Deal deal) throws CreateDealException {
        logger.info("Validating Deal Amount");
        if (deal.getAmount() == null || deal.getAmount().isEmpty()) {
            throw new CreateDealException("Deal Amount is Required");
        }
        try {
            Long.parseLong(deal.getAmount());
        } catch (NumberFormatException e) {
            throw new CreateDealException(e.getMessage());
        }
    }
}