package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User saveUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllUsers();

    void save(User user);

    Optional<User> findById(Long id);

    User update(Long id, User user);


    Optional<User> findUserByUsername(String username);


    void delete(Long id);

}
