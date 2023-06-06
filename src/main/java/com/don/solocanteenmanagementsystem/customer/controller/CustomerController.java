package com.don.solocanteenmanagementsystem.customer.controller;

import com.don.solocanteenmanagementsystem.customer.dto.CustomerRequestDto;
import com.don.solocanteenmanagementsystem.customer.model.Customer;
import com.don.solocanteenmanagementsystem.customer.service.CustomerService;
import com.don.solocanteenmanagementsystem.customer.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final TokenService tokenService;

    @PostMapping("/token")
    public String getToken(Authentication authentication){
        return tokenService.generateToken(authentication);
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/don")
    public String hi(){
        return "hell";
    }

    @PostMapping("/customers")
    public String createNewCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        long id = customerService.createCustomer(customerRequestDto);
        return "Customer with id " + id + " created successfully";
    }

    @GetMapping("/customers/{id}")
    public Customer fetchCustomerById(@PathVariable long id) {
        return customerService.findCustomerById(id);
    }

}

