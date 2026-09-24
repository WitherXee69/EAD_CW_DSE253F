package com.witherxee.petclinic.service;

import com.witherxee.petclinic.dao.CustomerDAO;
import com.witherxee.petclinic.model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(){
        customerDAO = new CustomerDAO();
    }

    public void addCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    public void deleteCustomer(int id) {
        customerDAO.delete(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }
}
