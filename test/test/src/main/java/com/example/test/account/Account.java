package com.example.test.account;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Класс для представления таблицы account
@Setter
@Getter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

}
