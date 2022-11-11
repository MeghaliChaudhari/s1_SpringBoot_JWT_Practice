package com.example.practice.s1_practice.service;

import com.example.practice.s1_practice.domain.Customer;
import com.example.practice.s1_practice.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    public Customer addCustomer(Customer customer);

    public List<Customer> getCustomer();

    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException;

    public Customer findByCustomerNameAndCustomerPassword(String customerName,String customerPassword) throws CustomerNotFoundException;
}
