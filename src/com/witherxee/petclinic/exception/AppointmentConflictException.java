package com.witherxee.petclinic.exception;

public class AppointmentConflictException extends Exception {
    public AppointmentConflictException(String message) {
        super(message);
    }
}
