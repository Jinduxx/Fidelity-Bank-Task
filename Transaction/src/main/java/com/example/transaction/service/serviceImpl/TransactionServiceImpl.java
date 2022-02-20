package com.example.transaction.service.serviceImpl;

import com.example.transaction.payload.TransactionRequestDto;
import com.example.transaction.payload.TransactionResponseDto;
import com.example.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final RestTemplate restTemplate;

    @Autowired
    public TransactionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TransactionResponseDto makeTransaction(TransactionRequestDto transactionRequestDto) {
        return restTemplate.postForObject("http://localhost:8091/account/transact",
                transactionRequestDto, TransactionResponseDto.class);
    }
}
