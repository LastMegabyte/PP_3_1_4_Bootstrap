package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    void addUser(User user);

    void updateUser(User user);

    //    void changeUser(long id, User updatedUser);
    void deleteUser(long id);

    User getUserById(long id);
}
