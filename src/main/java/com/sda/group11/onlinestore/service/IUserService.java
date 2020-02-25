package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User saveUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllUsers();

    void save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User update(Long id, User user);

<<<<<<< HEAD
=======
    Optional<User> findUserByUsername(String username);

    List<User> findUserByRole(Role role);

    User createUser(User user);

>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
    void delete(Long id);

}
