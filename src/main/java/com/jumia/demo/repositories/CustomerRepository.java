package com.jumia.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumia.demo.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
