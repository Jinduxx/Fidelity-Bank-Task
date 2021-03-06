package com.example.accountdetail.payload;

import com.example.accountdetail.enumeration.TRANSACTION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private BigDecimal amount;
    private Long accountNumber;
    private String userName;
    private TRANSACTION_TYPE transactionType;
}
