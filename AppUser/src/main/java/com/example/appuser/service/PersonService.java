package com.example.appuser.service;

import com.example.appuser.model.BankAccount;
import com.example.appuser.payload.*;

public interface PersonService {


    AuthResponse loginUser(AuthRequest req) throws Exception;

    PersonResponse register(PersonRequest personRequest) ;

    PersonResponse getUser(String username);

    PersonResponse addNewAccount(AddAccountDto addAccountDto);
}
