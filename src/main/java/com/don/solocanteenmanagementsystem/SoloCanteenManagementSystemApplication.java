package com.don.solocanteenmanagementsystem;

import com.don.solocanteenmanagementsystem.customer.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SoloCanteenManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoloCanteenManagementSystemApplication.class, args);
    }

}
