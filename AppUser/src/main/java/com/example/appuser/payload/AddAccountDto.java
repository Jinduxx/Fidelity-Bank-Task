package com.example.appuser.payload;

import com.example.appuser.model.BankAccount;
import lombok.Data;

@Data
public class AddAccountDto {

    private String username;

    private BankAccount bankAccount;
}
