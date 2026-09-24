package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.PetDAO;
import com.witherxee.petclinic.model.Pet;

import java.util.List;

public class PetService {
    private final PetDAO petDAO;

    public PetService() {
        petDAO = new PetDAO();
    }

    public void addPet(Pet pet) {
        petDAO.create(pet);
    }

    public void updatePet(Pet pet) {
        petDAO.update(pet);
    }

    public void deletePet(int id) {
        petDAO.delete(id);
    }

    public List<Pet> getAllPets() {
        return petDAO.findAll();
    }

    public Pet getPetById(int id) {
        return petDAO.findById(id);
    }

    public List<Pet> getPetsByOwnerId(int ownerId) {
        return petDAO.findByOwnerId(ownerId);
    }
}
