package com.witherxee.petclinic.model;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int appointmentId;
    private double amount;
    private String status;
    private LocalDate paymentDate;
    private String paymentMethod;

    public Payment(){}

    public Payment(int paymentId, int appointmentId, double amount, LocalDate paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    //Setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}