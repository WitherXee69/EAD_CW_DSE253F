
package com.witherxee.petclinic.controller;

import com.witherxee.petclinic.model.Customer;
import com.witherxee.petclinic.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController() {
        customerService = new CustomerService();
    }

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerService.deleteCustomer(id);
    }

    public Customer getCustomerById(int id) {
        return customerService.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}