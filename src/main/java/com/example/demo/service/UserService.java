package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    /**
     * Объект репозитория.
     */
    private final UserRepository userRepository;

    /**
     * Получение всех пользователей.
     * @return Список пользователей.
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Сохранение пользователя.
     * @param user Объект сохраняемого пользователя.
     * @return Сохраненный пользователь.
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя.
     * @param id Идентификатор пользователя.
     */
    public void deleteById(int id){ userRepository.deleteById(id);}

    /**
     * Получение пользователя по ID.
     * @param id Идентификатор пользователя.
     * @return Найденный пользователь.
     */
    public User findOneById(int id){ return  userRepository.findOneById(id);}

    /**
     * Обновление пользователя.
     * @param user Объект обновляемого пользователя.
     * @return Обновленный пользователь.
     */
    public  User updateUser(User user) { return  userRepository.updateUser(user);}

}
