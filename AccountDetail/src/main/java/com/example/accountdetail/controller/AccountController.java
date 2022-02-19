package com.example.accountdetail.controller;

import com.example.accountdetail.payload.AccountResponse;
import com.example.accountdetail.payload.CreateAccountDto;
import com.example.accountdetail.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/getAll")
//    public ResponseEntity<PersonResponse> getUserAccounts(@Valid @RequestBody String username) {
//        return new ResponseEntity<>(accountService.createAccount(createAccountDto), HttpStatus.OK);
//    }
}
