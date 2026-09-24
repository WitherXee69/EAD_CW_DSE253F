package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Veterinarian;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianDAO implements BaseDAO<Veterinarian> {

    @Override
    public void create(Veterinarian vet) {
        String sql = "INSERT INTO veterinarian " +
                "(name, phone, email, address, license_number, specialization) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vet.getName());
            statement.setString(2, vet.getPhoneNumber());
            statement.setString(3, vet.getEmail());
            statement.setString(4, vet.getAddress());
            statement.setString(5, vet.getLicenseNumber());
            statement.setString(6, vet.getSpecialization());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Veterinarian findById(int id) {
        String sql = "SELECT * FROM veterinarian WHERE veterinarian_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return mapVeterinarian(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Veterinarian> findAll() {
        List<Veterinarian> vets = new ArrayList<>();

        String sql = "SELECT * FROM veterinarian";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                vets.add(mapVeterinarian(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vets;
    }

    @Override
    public void update(Veterinarian vet) {
        String sql = "UPDATE veterinarian SET name = ?, phone = ?, email = ?, " +
                "address = ?, license_number = ?, specialization = ? " +
                "WHERE veterinarian_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vet.getName());
            statement.setString(2, vet.getPhoneNumber());
            statement.setString(3, vet.getEmail());
            statement.setString(4, vet.getAddress());
            statement.setString(5, vet.getLicenseNumber());
            statement.setString(6, vet.getSpecialization());
            statement.setInt(7, vet.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM veterinarian WHERE veterinarian_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Veterinarian mapVeterinarian(ResultSet result) throws SQLException {
        Veterinarian vet = new Veterinarian();
        vet.setId(result.getInt("veterinarian_id"));
        vet.setName(result.getString("name"));
        vet.setPhoneNumber(result.getString("phone"));
        vet.setEmail(result.getString("email"));
        vet.setAddress(result.getString("address"));
        vet.setLicenseNumber(result.getString("license_number"));
        vet.setSpecialization(result.getString("specialization"));
        return vet;
    }
}
