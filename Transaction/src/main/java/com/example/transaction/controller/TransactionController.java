package com.example.transaction.controller;

import com.example.transaction.enumerated.TRANSACTION_TYPE;
import com.example.transaction.payload.TransactionRequestDto;
import com.example.transaction.payload.TransactionResponseDto;
import com.example.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/creditTransaction")
    private ResponseEntity<TransactionResponseDto> creditTransact(@RequestBody TransactionRequestDto transactionRequestDto){
        transactionRequestDto.setTransactionType(TRANSACTION_TYPE.CREDIT);
        return new ResponseEntity<>(transactionService.makeTransaction(transactionRequestDto), HttpStatus.OK);
    }

    @PostMapping("/debitTransaction")
    private ResponseEntity<TransactionResponseDto> debitTransact(@RequestBody TransactionRequestDto transactionRequestDto){
        transactionRequestDto.setTransactionType(TRANSACTION_TYPE.DEBIT);
        return new ResponseEntity<>(transactionService.makeTransaction(transactionRequestDto), HttpStatus.OK);
    }
}
