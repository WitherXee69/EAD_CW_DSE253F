
package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Treatment;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO implements BaseDAO<Treatment> {

    @Override
    public void create(Treatment treatment) {
        String sql = "INSERT INTO treatment "
                + "(appointment_id, treatment_description, medication, cost) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, treatment.getAppointmentId());
            ps.setString(2, treatment.getName());
            ps.setString(3, treatment.getDescription());
            ps.setDouble(4, treatment.getCost());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Treatment findById(int id) {
        String sql = "SELECT * FROM treatment WHERE treatment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTreatment(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Treatment> findAll() {
        List<Treatment> list = new ArrayList<>();
        String sql = "SELECT * FROM treatment ORDER BY treatment_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapTreatment(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Treatment> findByAppointmentId(int appointmentId) {
        List<Treatment> list = new ArrayList<>();

        String sql = "SELECT * FROM treatment WHERE appointment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapTreatment(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void update(Treatment treatment) {
        String sql = "UPDATE treatment SET "
                + "appointment_id = ?, "
                + "treatment_description = ?, "
                + "medication = ?, cost = ? "
                + "WHERE treatment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, treatment.getAppointmentId());
            ps.setString(2, treatment.getName());
            ps.setString(3, treatment.getDescription());
            ps.setDouble(4, treatment.getCost());
            ps.setInt(5, treatment.getTreatmentId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM treatment WHERE treatment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Treatment mapTreatment(ResultSet rs)
            throws SQLException {

        Treatment treatment = new Treatment();

        treatment.setTreatmentId(rs.getInt("treatment_id"));
        treatment.setAppointmentId(rs.getInt("appointment_id"));

        // Map database columns to existing model properties.
        treatment.setName(
                rs.getString("treatment_description"));

        treatment.setDescription(
                rs.getString("medication"));

        treatment.setCost(rs.getDouble("cost"));

        return treatment;
    }
}