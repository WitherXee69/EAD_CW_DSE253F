
package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Veterinarian;
import com.witherxee.petclinic.service.VeterinarianService;

import java.util.List;

public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    public VeterinarianController() {
        veterinarianService = new VeterinarianService();
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarianService.addVeterinarian(veterinarian);
    }

    public void updateVeterinarian(Veterinarian veterinarian) {
        veterinarianService.updateVeterinarian(veterinarian);
    }

    public void deleteVeterinarian(int id) {
        veterinarianService.deleteVeterinarian(id);
    }

    public Veterinarian getVeterinarianById(int id) {
        return veterinarianService.getVeterinarianById(id);
    }

    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianService.getAllVeterinarians();
    }
}