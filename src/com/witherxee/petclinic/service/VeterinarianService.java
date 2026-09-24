package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.VeterinarianDAO;
import com.witherxee.petclinic.model.Veterinarian;

import java.util.List;

public class VeterinarianService {
    private final VeterinarianDAO veterinarianDAO;

    public VeterinarianService() {
        veterinarianDAO = new VeterinarianDAO();
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarianDAO.create(veterinarian);
    }

    public void updateVeterinarian(Veterinarian veterinarian) {
        veterinarianDAO.update(veterinarian);
    }

    public void deleteVeterinarian(int id) {
        veterinarianDAO.delete(id);
    }

    public Veterinarian getVeterinarianById(int id) {
        return veterinarianDAO.findById(id);
    }

    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianDAO.findAll();
    }
}
