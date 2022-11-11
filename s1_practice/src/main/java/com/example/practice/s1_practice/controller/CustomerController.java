package com.example.practice.s1_practice.controller;

import com.example.practice.s1_practice.domain.Customer;
import com.example.practice.s1_practice.exception.CustomerNotFoundException;
import com.example.practice.s1_practice.service.CustomerServiceImpl;
import com.example.practice.s1_practice.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CustomerController {
    private CustomerServiceImpl customerServiceImpl;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl,SecurityTokenGenerator securityTokenGenerator) {
        this.customerServiceImpl = customerServiceImpl;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
        ResponseEntity responseEntity = null;
        Map<String,String> map = null;
        try {
            Customer customer1 = customerServiceImpl.findByCustomerNameAndCustomerPassword(customer.getCustomerName(), customer.getCustomerPassword());
            if (customer1.getCustomerName().equals(customer.getCustomerName())){
                map = securityTokenGenerator.generateToken(customer);
            }
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        }catch (CustomerNotFoundException e){
            throw new CustomerNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Try After Sometimes",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerServiceImpl.addCustomer(customer),HttpStatus.CREATED);
    }

    @GetMapping("/data/v1/info")
    public ResponseEntity<?> getCustomers(){
        return new ResponseEntity<>(customerServiceImpl.getCustomer(),HttpStatus.OK);
    }

    @DeleteMapping("/data/v1/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable ("customerId") int customerId) throws CustomerNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            customerServiceImpl.deleteCustomer(customerId);
            responseEntity = new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
        }catch (CustomerNotFoundException e){
            throw new CustomerNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
