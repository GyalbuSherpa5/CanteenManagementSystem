package com.don.solocanteenmanagementsystem.customer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {
    private String name;
    private int phoneNumber;
}
