package com.witherxee.petclinic.model;

public class Customer extends Person {
    private int customerId;

    public Customer(int id, String name, String phoneNumber, String email, String address, int customerId) {
        super(id, name, phoneNumber, email, address);
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}