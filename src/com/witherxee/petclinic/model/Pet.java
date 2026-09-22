package com.witherxee.petclinic.model;

import java.time.LocalDate;

public class Pet {
    private int petId;
    private int ownerId;
    private String name;
    private String species;
    private LocalDate dob;
    private char gender;
    private String breed;

    public Pet(int petId, String name, String species, LocalDate dob, int ownerId, char gender, String breed) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.dob = dob;
        this.ownerId = ownerId;
        this.gender = gender;
        this.breed = breed;
    }

    //Getters
    public int getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getAge() {
        return dob;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public char getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    //Setters
    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(LocalDate dob) {
        this.dob = dob;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}