
package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Payment;
import com.witherxee.petclinic.service.PaymentService;
import com.witherxee.petclinic.exception.InvalidAppointmentException;

import java.util.List;

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController() {
        paymentService = new PaymentService();
    }

    public void addPayment(Payment payment)
            throws InvalidAppointmentException {
        paymentService.addPayment(payment);
    }

    public void updatePayment(Payment payment) {
        paymentService.updatePayment(payment);
    }

    public void deletePayment(int id) {
        paymentService.deletePayment(id);
    }

    public Payment getPayment(int id) {
        return paymentService.getPayment(id);
    }

    public Payment getPaymentByAppointment(int appointmentId) {
        return paymentService.getPaymentByAppointment(appointmentId);
    }

    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}