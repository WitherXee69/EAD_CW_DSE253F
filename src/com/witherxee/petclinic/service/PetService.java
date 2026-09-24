package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.PetDAO;
import com.witherxee.petclinic.dao.CustomerDAO;

import com.witherxee.petclinic.exception.MissingOwnerException;

import com.witherxee.petclinic.model.Pet;
import com.witherxee.petclinic.model.Customer;

import java.util.List;

public class PetService {
    private final PetDAO petDAO;
    private final CustomerDAO customerDAO;

    public PetService() {
        petDAO = new PetDAO();
        customerDAO = new CustomerDAO();
    }

    public void addPet(Pet pet) throws MissingOwnerException {
        Customer owner = customerDAO.findById(pet.getOwnerId());
        if (owner == null) {
            throw new MissingOwnerException("Owner not found for the given pet.");
        }
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
