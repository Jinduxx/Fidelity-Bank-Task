package com.example.accountdetail.payload;

import com.example.accountdetail.enumeration.TRANSACTION_TYPE;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {

    private BigDecimal amount;
    private Long accountNumber;
    private String userName;
    private TRANSACTION_TYPE transactionType;
}
