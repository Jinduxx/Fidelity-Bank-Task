package com.example.transaction.service;

import com.example.transaction.payload.TransactionRequestDto;
import com.example.transaction.payload.TransactionResponseDto;

public interface TransactionService {
    TransactionResponseDto makeTransaction(TransactionRequestDto transactionRequestDto);
}
