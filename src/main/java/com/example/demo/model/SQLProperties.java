package com.example.demo.model;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "sql")
@Data
public class SQLProperties {
    /**
     * Строка запроса на получение всех пользователей.
     */
    private  String getAll;
    /**
     * Строка запроса на сохранения пользователя.
     */
    private  String save;
    /**
     * Строка запроса на удаление пользователя по ID.
     */
    private  String deleteById;
    /**
     * Строка запроса на получение пользователя по ID.
     */
    private  String findOneById;
    /**
     * Строка запроса на обновления пользователя по ID.
     */
    private  String updateById;
}
