package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Appointment;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements BaseDAO<Appointment> {

    @Override
    public void create(Appointment appointment) {
        String sql = "INSERT INTO appointment " +
                "(pet_id, veterinarian_id, appointment_date, appointment_time, reason, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPetId());
            statement.setInt(2, appointment.getVeterinarianId());
            statement.setDate(3, Date.valueOf(appointment.getDate()));
            statement.setTime(4, Time.valueOf(appointment.getTime()));
            statement.setString(5, appointment.getReason());
            statement.setString(6, appointment.getStatus());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Appointment findById(int id) {
        String sql = "SELECT * FROM appointment WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return mapAppointment(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointment";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                appointments.add(mapAppointment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> findByPetId(int petId) {
        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointment WHERE pet_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, petId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                appointments.add(mapAppointment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> findByVeterinarianId(int veterinarianId) {
        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointment WHERE veterinarian_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, veterinarianId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                appointments.add(mapAppointment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public boolean isVeterinarianAvailable(
            int veterinarianId,
            LocalDate date,
            LocalTime time) {

        String sql = "SELECT COUNT(*) FROM appointment " +
                "WHERE veterinarian_id = ? " +
                "AND appointment_date = ? " +
                "AND appointment_time = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, veterinarianId);
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1) == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void update(Appointment appointment) {
        String sql = "UPDATE appointment SET " +
                "pet_id = ?, veterinarian_id = ?, appointment_date = ?, " +
                "appointment_time = ?, reason = ?, status = ? " +
                "WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPetId());
            statement.setInt(2, appointment.getVeterinarianId());
            statement.setDate(3, Date.valueOf(appointment.getDate()));
            statement.setTime(4, Time.valueOf(appointment.getTime()));
            statement.setString(5, appointment.getReason());
            statement.setString(6, appointment.getStatus());
            statement.setInt(7, appointment.getAppointmentId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Appointment mapAppointment(ResultSet result) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(result.getInt("appointment_id"));
        appointment.setPetId(result.getInt("pet_id"));
        appointment.setVeterinarianId(result.getInt("veterinarian_id"));
        appointment.setDate(result.getDate("appointment_date").toLocalDate());
        appointment.setTime(result.getTime("appointment_time").toLocalTime());
        appointment.setReason(result.getString("reason"));
        appointment.setStatus(result.getString("status"));
        return appointment;
    }
}