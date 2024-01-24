package com.example.demo.model;

import lombok.Data;



@Data
public class User {
    /**
     * Идентификатор пользователя.
     */
    private int id;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     * */
    private String lastName;


}
