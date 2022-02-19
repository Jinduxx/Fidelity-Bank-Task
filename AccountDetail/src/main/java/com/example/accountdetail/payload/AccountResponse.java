package com.example.accountdetail.payload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {

    private String userName;

    private Long accountNumber;

    private BigDecimal accountBalance;
}
