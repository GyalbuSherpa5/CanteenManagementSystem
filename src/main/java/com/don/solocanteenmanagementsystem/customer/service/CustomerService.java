package com.don.solocanteenmanagementsystem.customer.service;

import com.don.solocanteenmanagementsystem.customer.dto.CustomerRequestDto;
import com.don.solocanteenmanagementsystem.customer.model.Customer;

public interface CustomerService {
    long createCustomer(CustomerRequestDto customerRequestDto);

    Customer findCustomerById(long id);
}
