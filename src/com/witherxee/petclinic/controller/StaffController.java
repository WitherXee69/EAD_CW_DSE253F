package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Staff;
import com.witherxee.petclinic.service.StaffService;

public class StaffController {

    private final StaffService staffService;

    public StaffController() {
        staffService = new StaffService();
    }

    public Staff login(String username, String password) {
        return staffService.login(username, password);
    }
}