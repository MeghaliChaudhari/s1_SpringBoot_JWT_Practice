package com.example.practice.s1_practice.repository;

import com.example.practice.s1_practice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByCustomerNameAndCustomerPassword(String customerName,String customerPassword);
}
