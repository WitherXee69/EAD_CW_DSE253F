package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Pet;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO implements BaseDAO<Pet> {

    @Override
    public void create(Pet pet) {
        String sql = "INSERT INTO pet " +
                "(owner_id, name, species, breed, gender, date_of_birth) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pet.getOwnerId());
            statement.setString(2, pet.getName());
            statement.setString(3, pet.getSpecies());
            statement.setString(4, pet.getBreed());
            statement.setString(5, String.valueOf(pet.getGender()));
            statement.setDate(6, Date.valueOf(pet.getDateOfBirth()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pet findById(int id) {
        String sql = "SELECT * FROM pet WHERE pet_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return mapPet(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT * FROM pet";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                pets.add(mapPet(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    public List<Pet> findByOwnerId(int ownerId) {
        List<Pet> pets = new ArrayList<>();

        String sql = "SELECT * FROM pet WHERE owner_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, ownerId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                pets.add(mapPet(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public void update(Pet pet) {
        String sql = "UPDATE pet SET owner_id = ?, name = ?, species = ?, " +
                "breed = ?, gender = ?, date_of_birth = ? WHERE pet_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pet.getOwnerId());
            statement.setString(2, pet.getName());
            statement.setString(3, pet.getSpecies());
            statement.setString(4, pet.getBreed());
            statement.setString(5, String.valueOf(pet.getGender()));
            statement.setDate(6, Date.valueOf(pet.getDateOfBirth()));
            statement.setInt(7, pet.getPetId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pet WHERE pet_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pet mapPet(ResultSet result) throws SQLException {
        Pet pet = new Pet();
        pet.setPetId(result.getInt("pet_id"));
        pet.setOwnerId(result.getInt("owner_id"));
        pet.setName(result.getString("name"));
        pet.setSpecies(result.getString("species"));
        pet.setBreed(result.getString("breed"));
        pet.setGender(result.getString("gender").charAt(0));
        pet.setDateOfBirth(result.getDate("date_of_birth").toLocalDate());
        return pet;
    }
}