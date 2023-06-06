package com.don.solocanteenmanagementsystem.customer.repository;

import com.don.solocanteenmanagementsystem.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select customer_id from customer where phone_number=?1", nativeQuery = true)
    long getCustomerIdByPhoneNumber(int phone);

    Optional<Customer> findByPhoneNumberAndEmail(int phoneNumber, String email);

    Optional<Customer> findByUserName(String name);

}
