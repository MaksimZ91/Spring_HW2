package com.example.demo.repositories;

import com.example.demo.model.SQLProperties;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    /**
     * Объект коннектора к бд.
     */
    private final JdbcTemplate jdbc;

    /**
     * Объект с запросами к бд.
     */
    private final SQLProperties sqlProperties;

    /**
     * Получение всех пользователей.
     * @return Список всех пользователей.
     */
    public List<User> findAll() {

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        System.out.println(sqlProperties.getGetAll());

        return jdbc.query(sqlProperties.getGetAll(), userRowMapper);
    }

    /**
     * Сохранение пользователя в бд.
     * @param user Объект сохраняемого пользователя.
     * @return Сохраненный пользователь.
     */
    public User save(User user) {
        jdbc.update(sqlProperties.getSave(), user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Удаление пользователя из бд по ID.
     * @param id Идентификатор пользователя.
     */
    public void deleteById(int id) {
        jdbc.update(sqlProperties.getDeleteById(), id);
    }

    /**
     * Получение пользователя по ID
     * @param id Идентификатор пользователя.
     * @return Объект найденного пользователя.
     */
    public User findOneById(int id) {
        return jdbc.queryForObject(sqlProperties.getFindOneById(),
                (r, i) -> {
                    User user = new User();
                    user.setId(r.getInt("id"));
                    user.setFirstName(r.getString("firstName"));
                    user.setLastName(r.getString("lastName"));
                    return user;
                }, id);
    }

    /**
     * Обновление пользователя
     * @param user Объект обновляемого пользователя.
     * @return обновленный пользователь.
     */
    public User updateUser(User user) {
        jdbc.update(sqlProperties.getUpdateById(), user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}
