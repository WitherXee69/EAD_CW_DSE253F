package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.AppointmentDAO;
import com.witherxee.petclinic.exception.AppointmentConflictException;
import com.witherxee.petclinic.model.Appointment;

import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }

    public void bookAppointment(Appointment appointment)
            throws AppointmentConflictException {

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

    public void updateAppointment(Appointment appointment)
            throws AppointmentConflictException {

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