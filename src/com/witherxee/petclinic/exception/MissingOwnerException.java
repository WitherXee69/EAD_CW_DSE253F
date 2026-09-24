package com.witherxee.petclinic.exception;

public class MissingOwnerException extends Exception {
    public MissingOwnerException(String message) {
        super(message);
    }
}
