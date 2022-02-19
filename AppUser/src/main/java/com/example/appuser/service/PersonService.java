package com.example.appuser.service;

import com.example.appuser.model.BankAccount;
import com.example.appuser.payload.*;

import java.util.List;

public interface PersonService {


    AuthResponse loginUser(AuthRequest req) throws Exception;

    PersonResponse register(PersonRequest personRequest) ;

    PersonResponse getUser(String username);

    PersonResponse updateUserAccount(AddAccountDto addAccountDto);

    List<AccountResponseDto> getUserAccounts(String username);
}
