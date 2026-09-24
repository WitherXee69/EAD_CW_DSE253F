package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Payment;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment> {

    @Override
    public void create(Payment payment) {
        String sql = "INSERT INTO payment " +
                "(appointment_id, amount, payment_date, payment_method, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, payment.getAppointmentId());
            statement.setDouble(2, payment.getAmount());
            statement.setDate(3, Date.valueOf(payment.getPaymentDate()));
            statement.setString(4, payment.getPaymentMethod());
            statement.setString(5, payment.getStatus());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment findById(int id) {
        String sql = "SELECT * FROM payment WHERE payment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return mapPayment(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payment";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                payments.add(mapPayment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public Payment findByAppointmentId(int appointmentId) {
        String sql = "SELECT * FROM payment WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return mapPayment(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Payment payment) {
        String sql = "UPDATE payment SET " +
                "appointment_id = ?, amount = ?, payment_date = ?, " +
                "payment_method = ?, status = ? WHERE payment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, payment.getAppointmentId());
            statement.setDouble(2, payment.getAmount());
            statement.setDate(3, Date.valueOf(payment.getPaymentDate()));
            statement.setString(4, payment.getPaymentMethod());
            statement.setString(5, payment.getStatus());
            statement.setInt(6, payment.getPaymentId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM payment WHERE payment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Payment mapPayment(ResultSet result) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(result.getInt("payment_id"));
        payment.setAppointmentId(result.getInt("appointment_id"));
        payment.setAmount(result.getDouble("amount"));
        payment.setPaymentDate(result.getDate("payment_date").toLocalDate());
        payment.setPaymentMethod(result.getString("payment_method"));
        payment.setStatus(result.getString("status"));
        return payment;
    }
}