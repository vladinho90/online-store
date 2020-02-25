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
import java.util.Optional;

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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    //nu prea avem cum sa schimbam username ul
    @Override
    public User update(Long id, User user) {
        User userUpdate = findById(id).orElseThrow( () -> new RuntimeException("not found"));
        userUpdate.setAddress(user.getAddress());
        userUpdate.setOrdersList(user.getOrdersList());
       // userUpdate.setPassword(user.getPassword());
        userUpdate.setRole(user.getRole());
        userUpdate.setUsername(user.getUsername());
        userUpdate.setFullName(user.getFullName());
        return userRepository.save(userUpdate);
    }

    @Override
<<<<<<< HEAD
=======
    public Optional<User> findUserByUsername(String username)
    {
       return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> findUserByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public User createUser(User user) {

        User newUser = userRepository.save(user);
        //create cart
        Cart newCart = cartRepository.save(new Cart());
        newCart.setUser(newUser);
        return userRepository.save(newUser);
    }

    @Override
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
