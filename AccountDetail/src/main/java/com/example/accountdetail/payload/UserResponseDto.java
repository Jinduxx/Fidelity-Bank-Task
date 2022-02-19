package com.example.accountdetail.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
