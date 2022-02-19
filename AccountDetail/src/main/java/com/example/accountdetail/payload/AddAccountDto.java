package com.example.accountdetail.payload;

import com.example.accountdetail.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDto {

    private String username;
    private BankAccount bankAccount;
}
