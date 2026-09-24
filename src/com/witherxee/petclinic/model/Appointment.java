package com.witherxee.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentId;
    private int veterinarianId;
    private int petId;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private String status;

    public Appointment() {
    }

    public Appointment(int appointmentId, int customerId, int veterinarianId, int petId, LocalDate date, LocalTime time, String reason, String status) {
        this.appointmentId = appointmentId;
        this.veterinarianId = veterinarianId;
        this.petId = petId;
        this.reason = reason;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    // Getters
    public int getAppointmentId() {
        return appointmentId;
    }

    public int getVeterinarianId() {
        return veterinarianId;
    }

    public int getPetId() {
        return petId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setVeterinarianId(int veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}