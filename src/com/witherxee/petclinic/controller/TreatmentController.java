
package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Treatment;
import com.witherxee.petclinic.service.TreatmentService;
import com.witherxee.petclinic.exception.InvalidAppointmentException;

import java.util.List;

public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController() {
        treatmentService = new TreatmentService();
    }

    public void addTreatment(Treatment treatment)
            throws InvalidAppointmentException {
        treatmentService.addTreatment(treatment);
    }

    public void updateTreatment(Treatment treatment) {
        treatmentService.updateTreatment(treatment);
    }

    public void deleteTreatment(int id) {
        treatmentService.deleteTreatment(id);
    }

    public Treatment getTreatment(int id) {
        return treatmentService.getTreatment(id);
    }

    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    public List<Treatment> getTreatmentsByAppointment(int appointmentId) {
        return treatmentService.getTreatmentsByAppointment(appointmentId);
    }
}