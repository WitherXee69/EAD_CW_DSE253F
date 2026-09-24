package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Treatment;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO implements BaseDAO<Treatment> {

    @Override
    public void create(Treatment treatment) {
        String sql = "INSERT INTO treatment " +
                "(appointment_id, name, description, cost) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getAppointmentId());
            statement.setString(2, treatment.getName());
            statement.setString(3, treatment.getDescription());
            statement.setDouble(4, treatment.getCost());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Treatment findById(int id) {
        String sql = "SELECT * FROM treatment WHERE treatment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return mapTreatment(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Treatment> findAll() {
        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT * FROM treatment";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                treatments.add(mapTreatment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treatments;
    }

    public List<Treatment> findByAppointmentId(int appointmentId) {
        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT * FROM treatment WHERE appointment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                treatments.add(mapTreatment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treatments;
    }

    @Override
    public void update(Treatment treatment) {
        String sql = "UPDATE treatment SET " +
                "appointment_id = ?, name = ?, description = ?, cost = ? " +
                "WHERE treatment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getAppointmentId());
            statement.setString(2, treatment.getName());
            statement.setString(3, treatment.getDescription());
            statement.setDouble(4, treatment.getCost());
            statement.setInt(5, treatment.getTreatmentId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM treatment WHERE treatment_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Treatment mapTreatment(ResultSet result) throws SQLException {
        Treatment treatment = new Treatment();
        treatment.setTreatmentId(result.getInt("treatment_id"));
        treatment.setAppointmentId(result.getInt("appointment_id"));
        treatment.setName(result.getString("name"));
        treatment.setDescription(result.getString("description"));
        treatment.setCost(result.getDouble("cost"));
        return treatment;
    }
}