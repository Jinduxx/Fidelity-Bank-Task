package com.example.transaction.payload;

import com.example.transaction.enumerated.TRANSACTION_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {

    private BigDecimal amount;
    private Long accountNumber;
    private String userName;
    private TRANSACTION_TYPE transactionType;
}
