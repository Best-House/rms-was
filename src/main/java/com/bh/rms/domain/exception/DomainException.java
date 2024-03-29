package com.bh.rms.domain.exception;

public class DomainException extends RuntimeException{
    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if(message != null) {
            return message;
        }
        return getClass().getSimpleName();
    }
}
