package com.witherxee.petclinic.dao;

import com.witherxee.petclinic.model.Staff;
import com.witherxee.petclinic.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO {

    public Staff findByUsernameAndPassword(String username, String password) {

        String sql = "SELECT * FROM staff WHERE username = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Staff staff = new Staff();

                staff.setStaffId(result.getInt("staff_id"));
                staff.setUsername(result.getString("username"));
                staff.setPassword(result.getString("password"));
                staff.setName(result.getString("name"));
                staff.setRole(result.getString("role"));

                return staff;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}