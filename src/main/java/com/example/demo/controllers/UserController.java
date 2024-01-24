package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
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
@AllArgsConstructor
@Log
public class UserController {
    /**
     * Объект сервиса
     */
    private final UserService userService;

    /**
     * Получение всех пользователей.
     * @param model Объект модели для шаблонизатора.
     * @return шаблон со списком пользователей.
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Создание нового пользователя
     * @param user Объект пользователя
     * @return Шаблон с формой создания нового пользователя.
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    /**
     * Сохранения нового пользователя
     * @param user Объект пользователя
     * @return шаблон со списком пользователей.
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Удаление пользователя
     * @param id Идентификатор пользователя
     * @return шаблон со списком пользователей.
     */
    @GetMapping("user-delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Сохранение обновленного пользователя
     * @param user Объект пользователя
     * @return Шаблон с формой обновления пользователя.
     */
    @PostMapping("/user-update")
    public String updaterUserById(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    /**
     * Обновление пользователя
     * @param id Идентификатор пользователя
     * @param model Объект модели для шаблонизатора.
     * @return Шаблон с формой обновления пользователя.
     * * */
    @GetMapping("/user-update/{id}")
    public String userGetOne(@PathVariable("id") int id, Model model) {
        User user = userService.findOneById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

}
