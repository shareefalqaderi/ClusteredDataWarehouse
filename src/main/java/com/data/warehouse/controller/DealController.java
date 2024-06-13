package com.data.warehouse.controller;

import com.data.warehouse.exception.CreateDealException;
import com.data.warehouse.response.CreateDealResponse;
import com.data.warehouse.model.Deal;
import com.data.warehouse.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class DealController {

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDeal(@RequestBody Deal deal) {
        logger.info("Creating a deal");

        CreateDealResponse createDealResponse;
        HttpStatusCode httpStatusCode;
        try {
            createDealResponse = new CreateDealResponse(true, null, dealService.createDeal(deal));
            httpStatusCode = HttpStatus.OK;
        } catch (CreateDealException e) {
            createDealResponse = new CreateDealResponse(false, e.getMessage(), null);
            httpStatusCode = HttpStatus.BAD_REQUEST;
        }

        logger.info("Result of creating deal: {}", createDealResponse);
        return new ResponseEntity<>(createDealResponse, httpStatusCode);
    }
}
