package com.example.accountdetail.service.serviceImpl;

import com.example.accountdetail.enumeration.TRANSACTION_TYPE;
import com.example.accountdetail.exception.AccountException;
import com.example.accountdetail.model.BankAccount;
import com.example.accountdetail.payload.*;
import com.example.accountdetail.repository.AccountRepository;
import com.example.accountdetail.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(CreateAccountDto createAccountDto) {

        final String userName = createAccountDto.getUserName();
        UserResponseDto response = restTemplate.getForObject("http://localhost:8091/person/getPerson/" + userName, UserResponseDto.class);

        if (response == null) {
            throw new NullPointerException("User Not Found");
        }

        BankAccount request = new BankAccount();
        request.setAccount_type(createAccountDto.getAccount_type());
        request.setPin(1234L);
        request.setAccountBalance(BigDecimal.valueOf(0));
        Long accountNumber = generateAccountNumber();
        while (!isUnique(accountNumber)) {
            accountNumber = generateAccountNumber();
        }
        request.setAccountNumber(accountNumber);

        BankAccount bankAccount = accountRepository.save(request);

        AddAccountDto addAccountDto = new AddAccountDto();
        addAccountDto.setBankAccount(bankAccount);
        addAccountDto.setUsername(userName);
        updateAccount(addAccountDto);

        return modelMapper.map(bankAccount, AccountResponse.class);
    }

    public Long generateAccountNumber() {
        Random random = new Random();
        StringBuilder newAccountNumber = new StringBuilder();

        while (newAccountNumber.length() < 11) {
            int a = random.nextInt(10000000) + 1;
            newAccountNumber.append(a);
            }
        return Long.valueOf(newAccountNumber.toString());
    }

    public Boolean isUnique(Long accountNumber) {
        Optional<BankAccount> bankAccount = accountRepository.findByAccountNumber(accountNumber);
        return bankAccount.isEmpty();
    }

    @Override
    public AccountResponse transact(TransactionDto transactionDto) {

        final String userName = transactionDto.getUserName();
        UserResponseDto response = restTemplate.getForObject("http://localhost:8091/person/get/" + userName, UserResponseDto.class);

        if (response == null) {
            throw new AccountException("User Not Found");
        }

        Optional<BankAccount> account = accountRepository.findByAccountNumber(transactionDto.getAccountNumber());

        if (account.isEmpty()) {
            throw new AccountException("Account Not Found");
        }

        if (TRANSACTION_TYPE.CREDIT.equals(transactionDto.getTransactionType())) {
            account.get().setAccountBalance(account.get().getAccountBalance().add(transactionDto.getAmount()));
        } else {
            if (transactionDto.getAmount().compareTo(account.get().getAccountBalance()) > 0) {
                throw new AccountException("Insufficient balance to carry out transaction");
            }
            account.get().setAccountBalance(account.get().getAccountBalance().subtract(transactionDto.getAmount()));
        }

        BankAccount bankAccount = accountRepository.save(account.get());

        AddAccountDto addAccountDto = new AddAccountDto();
        addAccountDto.setBankAccount(bankAccount);
        addAccountDto.setUsername(userName);
        updateAccount(addAccountDto);

        return modelMapper.map(bankAccount, AccountResponse.class);
    }

    @Override
    public AccountResponse getBalance(Long accountNumber) {
        Optional<BankAccount> account = accountRepository.findByAccountNumber(accountNumber);

        if (account.isEmpty()) {
            throw new NullPointerException("Account not found");
        }
        return modelMapper.map(account.get(), AccountResponse.class);
    }

    private void updateAccount(AddAccountDto addAccountDto) {
        restTemplate.put("http://localhost:8091/person/account/update", addAccountDto);
    }
}
