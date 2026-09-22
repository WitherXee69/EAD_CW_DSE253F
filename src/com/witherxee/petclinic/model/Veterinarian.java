package com.witherxee.petclinic.model;

public class Veterinarian extends Person {
    private String licenseNumber;
    private String specialization;

    public Veterinarian(int id, String name, String phoneNumber, String email, String address, String licenseNumber, String specialization) {
        super(id, name, phoneNumber, email, address);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}