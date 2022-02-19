package com.example.appuser.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
