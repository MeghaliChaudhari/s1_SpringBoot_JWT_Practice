package com.example.practice.s1_practice.service;

import com.example.practice.s1_practice.domain.Customer;
import com.example.practice.s1_practice.exception.CustomerNotFoundException;
import com.example.practice.s1_practice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean res = false;
        if (customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException();
        }else {
            customerRepository.deleteById(customerId);
            res = true;
        }
        return res;
    }

    @Override
    public Customer findByCustomerNameAndCustomerPassword(String customerName, String customerPassword) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByCustomerNameAndCustomerPassword(customerName,customerPassword);
        if (customer == null){
            throw new CustomerNotFoundException();
        }
        return customer;
    }
}
