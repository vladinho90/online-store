package com.sda.group11.onlinestore.service.impl;


import com.sda.group11.onlinestore.model.Cart;
import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.repository.CartRepository;
import com.sda.group11.onlinestore.repository.UserRepository;
import com.sda.group11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    public CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user){
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Cart newCart = cartRepository.save(new Cart());
        newCart.setUser(user);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    //nu prea avem cum sa schimbam username ul
    @Override
    public User update(Long id, User user) {
        User userUpdate = findById(id);
        userUpdate.setAddress(user.getAddress());
        userUpdate.setOrdersList(user.getOrdersList());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setRole(user.getRole());
        userUpdate.setUsername(user.getUsername());
        return userRepository.save(userUpdate);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
