package com.example.appuser.controller;

import com.example.appuser.model.BankAccount;
import com.example.appuser.payload.*;
import com.example.appuser.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/register")
    public ResponseEntity<PersonResponse> register(@Valid @RequestBody PersonRequest personRequest) {
        return new ResponseEntity<>(personService.register(personRequest), HttpStatus.OK);
    }

    @PostMapping(path="/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) throws Exception {
        return ResponseEntity.ok().body(personService.loginUser(req));
    }

    @GetMapping(path="/getPerson/{username}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable("username") String username)  {
        return ResponseEntity.ok().body(personService.getUser(username));
    }

    @PutMapping(path="/update")
    public ResponseEntity<PersonResponse> addNewAccount(@RequestBody AddAccountDto addAccountDto)  {
        return ResponseEntity.ok().body(personService.addNewAccount(addAccountDto));
    }
}
