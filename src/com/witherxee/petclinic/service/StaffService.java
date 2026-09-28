package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.StaffDAO;
import com.witherxee.petclinic.model.Staff;

public class StaffService {

    private final StaffDAO staffDAO;

    public StaffService() {
        staffDAO = new StaffDAO();
    }

    public Staff login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        return staffDAO.findByUsernameAndPassword(
                username.trim(),
                password
        );
    }
}