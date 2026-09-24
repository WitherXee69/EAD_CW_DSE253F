package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.PaymentDAO;
import com.witherxee.petclinic.dao.AppointmentDAO;

import com.witherxee.petclinic.model.Payment;
import com.witherxee.petclinic.model.Appointment;

import com.witherxee.petclinic.exception.InvalidAppointmentException;

import java.util.List;

public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final AppointmentDAO appointmentDAO;

    public PaymentService() {
        paymentDAO = new PaymentDAO();
        appointmentDAO = new AppointmentDAO();
    }

    public void addPayment(Payment payment) throws InvalidAppointmentException {
        Appointment appointment = appointmentDAO.findById(payment.getAppointmentId());
        boolean isAmountValid = payment.getAmount() >= 0;
        if (!isAmountValid) {
            throw new InvalidAppointmentException("Payment amount cannot be negative.");
        } else if (appointment == null) {
            throw new InvalidAppointmentException("Appointment with ID " + payment.getAppointmentId() + " does not exist.");
        }
        paymentDAO.create(payment);
    }

    public Payment getPayment(int id) {
        return paymentDAO.findById(id);
    }

    public Payment getPaymentByAppointment(int appointmentId) {
        return paymentDAO.findByAppointmentId(appointmentId);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    public void updatePayment(Payment payment) {
        paymentDAO.update(payment);
    }

    public void deletePayment(int id) {
        paymentDAO.delete(id);
    }
}