package com.data.warehouse.exception;

public class CreateDealException extends RuntimeException {
    public CreateDealException(String message) {
        super(message);
    }

    public CreateDealException(String message, Throwable cause) {
        super(message, cause);
    }
}
