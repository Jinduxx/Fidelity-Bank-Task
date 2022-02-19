package com.example.appuser.payload;


import com.example.appuser.model.ACCOUNT_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private ACCOUNT_TYPE account_type;

    private BigDecimal accountBalance;
}
