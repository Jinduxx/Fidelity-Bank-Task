package com.example.accountdetail.payload;

import lombok.Data;

@Data
public class UserResponseDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
