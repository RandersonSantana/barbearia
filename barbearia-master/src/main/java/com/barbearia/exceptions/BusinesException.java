package com.barbearia.exceptions;

public class BusinesException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int errorCode = 400;

    public BusinesException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }
}

