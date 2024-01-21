package com.bh.rms.domain.exception;

public class InvalidAggregateIdException extends DomainException{

    public InvalidAggregateIdException() {
    }

    public InvalidAggregateIdException(String message) {
        super(message);
    }
}
