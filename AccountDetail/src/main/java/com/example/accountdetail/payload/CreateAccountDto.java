package com.example.accountdetail.payload;

import com.example.accountdetail.enumeration.ACCOUNT_TYPE;
import lombok.Data;

@Data
public class CreateAccountDto {

    private String userName;
    private ACCOUNT_TYPE account_type;

}
