package com.witherxee.petclinic.model;

public class Treatment {
    private int treatmentId;
    private int appointmentId;
    private String name;
    private String description;
    private double cost;

    public Treatment(int treatmentId, int appointmentId, String name, String description, double cost) {
        this.treatmentId = treatmentId;
        this.appointmentId = appointmentId;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // Getters
    public int getTreatmentId() {
        return treatmentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    //Setters
    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}