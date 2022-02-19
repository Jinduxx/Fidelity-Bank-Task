package com.example.appuser.service.serviceImpl;

import com.example.appuser.exception.CustomServiceExceptions;
import com.example.appuser.model.BankAccount;
import com.example.appuser.model.Person;
import com.example.appuser.payload.*;
import com.example.appuser.repository.PersonRepository;
import com.example.appuser.security.JwtUtils;
import com.example.appuser.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final PersonRepository personRepository;
    private final EmailValidator emailValidator;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PersonDetailsService userDetailsService;

    @Autowired
    public PersonServiceImpl(PasswordEncoder bCryptPasswordEncoder, PersonRepository personRepository,
                             EmailValidator emailValidator, ModelMapper modelMapper,
                             AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                             PersonDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personRepository = personRepository;
        this.emailValidator = emailValidator;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public AuthResponse loginUser(AuthRequest req) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),
                    req.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final PersonDetails person = userDetailsService.loadUserByUsername(req.getUsername());
            List<String> roles = person.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            final String jwt = jwtUtils.generateToken(person);
            final AuthResponse res = new AuthResponse();

            String role =null;
            for (String r : roles) {
                if (r!=null) role = r;
            }
            res.setToken(jwt);
            res.setRole(role);
            return res;
        } catch (Exception e) {
            throw new Exception("incorrect username or password!", e);
        }
    }

    @Override
    public PersonResponse register(PersonRequest personRequest) {
        boolean isValidEmail = emailValidator.test(personRequest.getEmail());
        if(!isValidEmail){
            throw new CustomServiceExceptions("Not a valid email");
        }

        boolean isValidNumber = emailValidator.validatePhoneNumber(personRequest.getPhoneNumber());

        if(!isValidNumber){
            throw new CustomServiceExceptions("Not a valid phone number");
        }

        boolean userExists = personRepository.findByEmail(personRequest.getEmail()).isPresent();
        if(userExists){
            throw  new CustomServiceExceptions("email taken");
        }

        Person person = new Person();
        modelMapper.map(personRequest, person);

        final String encodedPassword = bCryptPasswordEncoder.encode(personRequest.getPassword());
        person.setPassword(encodedPassword);

        personRepository.save(person);
        return PersonResponse.builder().firstName(person.getFirstName()).lastName(person.getLastName())
                .email(person.getEmail()).build();
    }

    @Override
    public PersonResponse getUser(String username) {
        Optional<Person> person = personRepository.findByUsername(username);

        if (person.isEmpty())
            return null;
        else
            return modelMapper.map(person.get(), PersonResponse.class);
    }

    @Override
    public PersonResponse addNewAccount(AddAccountDto addAccountDto) {
        Optional<Person> person = personRepository.findByUsername(addAccountDto.getUsername());

        if (person.isEmpty())
            return null;
        else
            person.get().getAccounts().add(addAccountDto.getBankAccount());
            return modelMapper.map(personRepository.save(person.get()), PersonResponse.class);
    }
}
