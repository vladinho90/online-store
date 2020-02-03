package com.sda.grupa11.onlinestore.service;

import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById (Long id){
        Optional<User> user= userRepository.findById(id);
        return user.orElseThrow(
                () -> new RuntimeException("user with id " + id + " does not exist")
        );
    }

    public List<User> findAll (){
        return userRepository.findAll();
    }

    public String save(User user){
        userRepository.save(user);
        return "user " + user + " was saved";
    }

    public String update(Long id, User user){
        User userToBeUpdated = findById(id);
       // userRepository.
        return null;
    }

    public String deleteById(Long id){
        userRepository.deleteById(id);
        return "user deleted successfully";
    }
}
