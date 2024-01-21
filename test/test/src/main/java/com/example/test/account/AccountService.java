package com.example.test.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register(String username, String password) {
        // Проверка, что пользователь с таким именем не существует
        if (accountRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }

        // Создание нового аккаунта
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        return accountRepository.save(account);
    }

    public Account login(String username, String password) {
        // Поиск аккаунта по имени пользователя
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new IllegalArgumentException("Аккаунт не найден");
        }

        // Проверка правильности пароля
        if (!account.getPassword().equals(password)) {
            throw new IllegalArgumentException("Неверный пароль");
        }

        return account;
    }
}
