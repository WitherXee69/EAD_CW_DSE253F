
package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Pet;
import com.witherxee.petclinic.service.PetService;
import com.witherxee.petclinic.exception.MissingOwnerException;

import java.util.List;

public class PetController {

    private final PetService petService;

    public PetController() {
        petService = new PetService();
    }

    public void addPet(Pet pet) throws MissingOwnerException {
        petService.addPet(pet);
    }

    public void updatePet(Pet pet) {
        petService.updatePet(pet);
    }

    public void deletePet(int id) {
        petService.deletePet(id);
    }

    public Pet getPetById(int id) {
        return petService.getPetById(id);
    }

    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    public List<Pet> getPetsByOwnerId(int ownerId) {
        return petService.getPetsByOwnerId(ownerId);
    }
}