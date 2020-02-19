package com.sda.group11.onlinestore.controller;

import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public IUserService userService;

   /* @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request){
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }*/

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id, Principal principal) {
        principal.getName();
        userService.deleteUserById(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id,
                                       @RequestBody User request, Principal principal) {
    principal.getName();
    User response = userService.updateUser(id, request);
    return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
