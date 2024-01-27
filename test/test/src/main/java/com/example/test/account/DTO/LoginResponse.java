package com.example.test.account.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private Long id;
    private String username;

    public LoginResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
