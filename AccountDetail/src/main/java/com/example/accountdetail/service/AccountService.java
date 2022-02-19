package com.example.accountdetail.service;

import com.example.accountdetail.payload.AccountResponse;
import com.example.accountdetail.payload.CreateAccountDto;

public interface AccountService {

    AccountResponse createAccount(CreateAccountDto createAccountDto);

}
