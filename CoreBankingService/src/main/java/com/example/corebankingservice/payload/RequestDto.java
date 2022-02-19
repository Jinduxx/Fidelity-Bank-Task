package com.example.corebankingservice.payload;

import com.example.corebankingservice.enumerated.TRANSACTION_TYPE;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDto {

    private Long accountNumber;
    private BigDecimal amount;
    private TRANSACTION_TYPE transaction_type;

}
