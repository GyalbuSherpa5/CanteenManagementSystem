package com.don.solocanteenmanagementsystem.customer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto {
    private String userName;
    private String password;
    private String address;
    private String email;
    private int phoneNumber;
}
