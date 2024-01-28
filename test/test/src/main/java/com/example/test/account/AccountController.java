package com.example.test.account;

import com.example.test.account.DTO.LoginRequest;
import com.example.test.account.DTO.LoginResponse;
import com.example.test.account.DTO.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Метод с аннотацией @PostMapping обрабатывает POST-запросы на эндпоинте "/register"
    // с целью регистрации (Создания) нового пользователя
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {
        try {
            Account account = accountService.register(registerRequest.getUsername(), registerRequest.getPassword());
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Метод с аннотацией @PostMapping обрабатывает POST-запросы на эндпоинте "/login"
    // с целью авторизации пользователя. Также, он возвращает в качестве ответа
    // id и username пользователя, если авторизация была успешна
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            Account account = accountService.login(loginRequest.getUsername(), loginRequest.getPassword());
            LoginResponse loginResponse = new LoginResponse(account.getId(), account.getUsername());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
