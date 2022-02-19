package com.example.appuser.controller;

import com.example.appuser.payload.*;
import com.example.appuser.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(path="/get/{username}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable("username") String username)  {
        return ResponseEntity.ok().body(personService.getUser(username));
    }

    @PutMapping(path="/account/update")
    public ResponseEntity<PersonResponse> updateUserAccount(@RequestBody AddAccountDto addAccountDto)  {
        return ResponseEntity.ok().body(personService.updateUserAccount(addAccountDto));
    }

    @GetMapping(path="/accounts")
    public ResponseEntity<List<AccountResponseDto>> getUserAccounts(@Valid @RequestParam String username) {
        return new ResponseEntity<>(personService.getUserAccounts(username), HttpStatus.OK);
    }
}
