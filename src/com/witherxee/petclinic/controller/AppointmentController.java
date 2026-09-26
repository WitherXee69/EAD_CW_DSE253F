
package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Appointment;
import com.witherxee.petclinic.service.AppointmentService;
import com.witherxee.petclinic.exception.AppointmentConflictException;

import java.util.List;

public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController() {
        appointmentService = new AppointmentService();
    }

    public void bookAppointment(Appointment appointment)
            throws AppointmentConflictException {
        appointmentService.bookAppointment(appointment);
    }

    public void updateAppointment(Appointment appointment)
            throws AppointmentConflictException {
        appointmentService.updateAppointment(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentService.deleteAppointment(id);
    }

    public Appointment getAppointment(int id) {
        return appointmentService.getAppointment(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    public List<Appointment> getAppointmentsByPet(int petId) {
        return appointmentService.getAppointmentsByPet(petId);
    }

    public List<Appointment> getAppointmentsByVeterinarian(
            int veterinarianId) {
        return appointmentService.getAppointmentsByVeterinarian(
                veterinarianId);
    }
}