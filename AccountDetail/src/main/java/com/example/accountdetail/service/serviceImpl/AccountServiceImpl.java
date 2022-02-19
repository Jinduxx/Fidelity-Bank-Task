package com.example.accountdetail.service.serviceImpl;

import com.example.accountdetail.model.BankAccount;
import com.example.accountdetail.payload.AccountResponse;
import com.example.accountdetail.payload.CreateAccountDto;
import com.example.accountdetail.payload.UserResponseDto;
import com.example.accountdetail.repository.AccountRepository;
import com.example.accountdetail.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

        UserResponseDto response = restTemplate.getForObject("http://localhost:8082/person/getPerson/" + createAccountDto.getUserName(), UserResponseDto.class);

        if (response == null) {
            throw new NullPointerException("User Not Found");
        }

        BankAccount request = new BankAccount();
        request.setAccount_type(createAccountDto.getAccount_type());
        request.setAccountNumber(generateAccountNumber());
        request.setPin(1234L);
        request.setAccountBalance(BigDecimal.valueOf(0));

        BankAccount bankAccount = accountRepository.save(request);

        UserResponseDto responseDto = restTemplate.postForObject("http://localhost:8082/person/update", bankAccount, UserResponseDto.class);
        return null;
    }

    public Long generateAccountNumber() {
        return 1234567890L;
//        Random random = new Random();
//        return (long) (random.nextInt(10000));
    }

//    @Override
//    public Optional<CreateUserDto> getUserAccounts(String account_no) {
//
//        // rest call to retreive user
//
//        return getUserAccounts();
//
//        return Optional.empty();
//    }
}
