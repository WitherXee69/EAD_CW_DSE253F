package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.TreatmentDAO;
import com.witherxee.petclinic.dao.AppointmentDAO;

import com.witherxee.petclinic.model.Treatment;
import com.witherxee.petclinic.model.Appointment;

import com.witherxee.petclinic.exception.InvalidAppointmentException;

import java.util.List;

public class TreatmentService {
    private final TreatmentDAO treatmentDAO;
    private final AppointmentDAO appointmentDAO;

    public TreatmentService() {
        treatmentDAO = new TreatmentDAO();
        appointmentDAO = new AppointmentDAO();
    }

    public void addTreatment(Treatment treatment) throws InvalidAppointmentException {
        Appointment appointment = appointmentDAO.findById(treatment.getAppointmentId());
        boolean IsCostValid = treatment.getCost() >= 0;
        if (!IsCostValid) {
            throw new InvalidAppointmentException("Treatment cost cannot be negative.");
        } else if (appointment == null) {
            throw new InvalidAppointmentException("Appointment with ID " + treatment.getAppointmentId() + " does not exist.");
        }
        treatmentDAO.create(treatment);
    }

    public Treatment getTreatment(int id) {
        return treatmentDAO.findById(id);
    }

    public List<Treatment> getAllTreatments() {
        return treatmentDAO.findAll();
    }

    public List<Treatment> getTreatmentsByAppointment(int appointmentId) {
        return treatmentDAO.findByAppointmentId(appointmentId);
    }

    public void updateTreatment(Treatment treatment) {
        treatmentDAO.update(treatment);
    }

    public void deleteTreatment(int id) {
        treatmentDAO.delete(id);
    }
}