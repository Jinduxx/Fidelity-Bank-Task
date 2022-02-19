package com.example.accountdetail.controller;

import com.example.accountdetail.payload.AccountResponse;
import com.example.accountdetail.payload.CreateAccountDto;
import com.example.accountdetail.payload.TransactionDto;
import com.example.accountdetail.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountDto createAccountDto) {
        return new ResponseEntity<>(accountService.createAccount(createAccountDto), HttpStatus.OK);
    }

    @PostMapping("/transact")
    public ResponseEntity<AccountResponse> transact(@Valid @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(accountService.transact(transactionDto), HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<AccountResponse> getAccountBalance(@Valid @RequestParam Long accountNumber) {
        return new ResponseEntity<>(accountService.getBalance(accountNumber), HttpStatus.OK);
    }
}
