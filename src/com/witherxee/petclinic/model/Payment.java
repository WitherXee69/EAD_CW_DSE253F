package com.witherxee.petclinic.model;

public class Payment {
    private int paymentId;
    private int appointmentId;
    private double amount;
    private String currency;
    private String paymentMethod;

    public Payment(int paymentId, int appointmentId, double amount, String currency, String paymentMethod) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
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

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}