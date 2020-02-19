package com.sda.group11.onlinestore.controller;

import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;
import com.sda.group11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(UserController.API_USER)
public class UserController {

    public static final String API_USER = "api/user";

    @Autowired
    public IUserService userService;

   /* @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request){
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }*/

    @PostMapping("/registration")
    public ResponseEntity<User> create(@RequestBody User user) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id, Principal principal) {
        principal.getName();
        userService.delete(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id,
                                       @RequestBody User request, Principal principal) {
        principal.getName();
        User response = userService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
