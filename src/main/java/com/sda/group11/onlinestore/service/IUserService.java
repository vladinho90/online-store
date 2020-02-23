package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User update(Long id, User user);

    Optional<User> findUserByUsername(String username);

    List<User> findUserByRole(Role role);

    User createUser(User user);

    void delete(Long id);
}
