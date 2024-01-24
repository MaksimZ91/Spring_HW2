## Урок 2. Основы Spring. Spring Boot
Базовое задание:  
Добавить в простое CRUD веб-приложение, которое было разработано на семинаре функцию удаления данных о пользователе:  
1) В класс UserRepository добавить метод public void deleteById(int id)(подсказка: SQL запрос "DELETE FROM userTable WHERE id=?", метод jdbc.update) - удаления записи пользователя из БД по ID.  
2) В класс UserService добавить метод public void deleteById(int id)(подсказка: в нем вызывается метод userRepository.deleteById) - удаление пользователя через репозиторий.  
3) В класс UserController добавить метод public String deleteUser(@PathVariable("id") int id)(с аннотацией: @GetMapping("user-delete/{id}")) (подсказка: в нем вызывается метод userService.deleteById) - перехват команды на удаление студента от браузера.  

Если задание выполнено верно, то при запуске приложения по адресу http://localhost:8080/users будет работать кнопка удаления пользователя по ID.  
![1](https://github.com/MaksimZ91/Spring_HW2/assets/72209139/19cc55f8-0dea-43d7-b047-53e850fb41cd)
![2](https://github.com/MaksimZ91/Spring_HW2/assets/72209139/2722d95d-d595-4df7-ab1c-73f5707fc6d8)

Задание "со звездочкой":  
Реализовать метод обновления данных о пользователе.  
- @GetMapping("/user-update/{id}")  
- @PostMapping("/user-update")  
- User update(User user)  
- User getOne(int id)
  
  ![3](https://github.com/MaksimZ91/Spring_HW2/assets/72209139/c33d5080-b3b0-406f-933b-dad21f4da715)
  ![4](https://github.com/MaksimZ91/Spring_HW2/assets/72209139/c6445d7f-44e7-48df-a3e4-e0cb96e509f7)

### UserController class
```java
package com.example.demo.controllers;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/user-update")
    public String updaterUserById(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String userGetOne(@PathVariable("id") int id, Model model) {
        User user = userService.findOneById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

}
```
### UserService class
```java 
@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(int id){ userRepository.deleteById(id);}

    public User findOneById(int id){ return  userRepository.findOneById(id);}

    public  User updateUser(User user) { return  userRepository.updateUser(user);}

}
```
### UserRepository Class
```java
package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    public User findOneById(int id) {
        String sql = "SELECT * FROM userTable WHERE id=?";
        return jdbc.queryForObject(sql,
                (r, i) -> {
                    User user = new User();
                    user.setId(r.getInt("id"));
                    user.setFirstName(r.getString("firstName"));
                    user.setLastName(r.getString("lastName"));
                    return user;
                }, id);
    }

    public  User updateUser(User user){
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}

```

  
