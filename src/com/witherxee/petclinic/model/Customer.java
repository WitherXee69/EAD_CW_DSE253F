package com.witherxee.petclinic.model;

public class Customer extends Person {

    public Customer(){}

    public Customer(int id, String name, String phoneNumber, String email, String address) {
        super(id, name, phoneNumber, email, address);
    }
}