package com.don.solocanteenmanagementsystem.customer.service;

import com.don.solocanteenmanagementsystem.customer.dto.CustomerRequestDto;
import com.don.solocanteenmanagementsystem.customer.exception.CustomerAlreadyExistException;
import com.don.solocanteenmanagementsystem.customer.exception.CustomerDoNotExistException;
import com.don.solocanteenmanagementsystem.customer.model.Customer;
import com.don.solocanteenmanagementsystem.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public long createCustomer(CustomerRequestDto customerRequestDto) {

        Optional<Customer> customerCheck =
                customerRepository.findByPhoneNumberAndEmail(customerRequestDto.getPhoneNumber(),
                        customerRequestDto.getEmail());
        Customer customer;

        if (customerCheck.isPresent()) {
            throw new CustomerAlreadyExistException("customer already exist");
        } else {
            customer = Customer.builder()
                    .userName(customerRequestDto.getUserName())
                    .role("user")
                    .password(passwordEncoder.encode(customerRequestDto.getPassword()))
                    .address(customerRequestDto.getAddress())
                    .email(customerRequestDto.getEmail())
                    .phoneNumber(customerRequestDto.getPhoneNumber())
                    .build();
            customerRepository.save(customer);
        }

        return customerRepository.getCustomerIdByPhoneNumber(customer.getPhoneNumber());
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerDoNotExistException("Product with id: " + id + " doesn't exist"));
    }
}
