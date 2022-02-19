package com.example.accountdetail.service;

import com.example.accountdetail.payload.AccountResponse;
import com.example.accountdetail.payload.CreateAccountDto;
import com.example.accountdetail.payload.TransactionDto;

public interface AccountService {

    AccountResponse createAccount(CreateAccountDto createAccountDto);

    AccountResponse transact(TransactionDto transactionDto);

    AccountResponse getBalance(Long accountNumber);
}
