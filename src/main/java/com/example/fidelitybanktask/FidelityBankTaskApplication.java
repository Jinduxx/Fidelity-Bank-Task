package com.example.fidelitybanktask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class FidelityBankTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FidelityBankTaskApplication.class, args);
    }

}
