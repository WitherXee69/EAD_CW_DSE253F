package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.AppointmentDAO;
import com.witherxee.petclinic.dao.PetDAO;
import com.witherxee.petclinic.dao.VeterinarianDAO;

import com.witherxee.petclinic.exception.AppointmentConflictException;

import com.witherxee.petclinic.model.Appointment;
import com.witherxee.petclinic.model.Pet;
import com.witherxee.petclinic.model.Veterinarian;

import com.witherxee.petclinic.util.DateTimeValidator;

import java.util.List;

public class AppointmentService {
    private final AppointmentDAO appointmentDAO;
    private final PetDAO petDAO;
    private final VeterinarianDAO veterinarianDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
        petDAO = new PetDAO();
        veterinarianDAO = new VeterinarianDAO();
    }

    public void bookAppointment(Appointment appointment) throws AppointmentConflictException {
        Pet pet = petDAO.findById(appointment.getPetId());
        Veterinarian veterinarian = veterinarianDAO.findById(appointment.getVeterinarianId());
        boolean available = appointmentDAO.isVeterinarianAvailable(
                appointment.getVeterinarianId(),
                appointment.getDate(),
                appointment.getTime()
        );
        boolean isDateTimeValid = DateTimeValidator.isValidAndFuture(
                appointment.getDate() + " " + appointment.getTime(), "yyyy-MM-dd HH:mm");

        if (!available) {
            throw new AppointmentConflictException(
                    "The veterinarian is already booked for this date and time."
            );
        } else if (pet == null) {
            throw new AppointmentConflictException(
                    "The pet does not exist."
            );
        } else if (veterinarian == null) {
            throw new AppointmentConflictException(
                    "The veterinarian does not exist."
            );
        } else if (!isDateTimeValid) {
            throw new AppointmentConflictException(
                    "The appointment date and time must be valid"
            );
        }

        appointmentDAO.create(appointment);
    }

    public Appointment getAppointment(int id) {
        return appointmentDAO.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.findAll();
    }

    public List<Appointment> getAppointmentsByPet(int petId) {
        return appointmentDAO.findByPetId(petId);
    }

    public List<Appointment> getAppointmentsByVeterinarian(int veterinarianId) {
        return appointmentDAO.findByVeterinarianId(veterinarianId);
    }

    public void updateAppointment(Appointment appointment) throws AppointmentConflictException {

        boolean available = appointmentDAO.isVeterinarianAvailable(
                appointment.getVeterinarianId(),
                appointment.getDate(),
                appointment.getTime()
        );

        if (!available) {
            throw new AppointmentConflictException(
                    "The veterinarian is already booked for this date and time."
            );
        }

        appointmentDAO.update(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentDAO.delete(id);
    }
}