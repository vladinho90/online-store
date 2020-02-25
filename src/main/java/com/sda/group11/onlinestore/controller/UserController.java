package com.sda.group11.onlinestore.controller;

<<<<<<< HEAD
import com.sda.group11.onlinestore.jwt.JwtTokenProvider;
=======
import com.sda.group11.onlinestore.dto.user.UserMapper;
import com.sda.group11.onlinestore.dto.user.UserResponse;
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;
import com.sda.group11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(UserController.API_USER)
public class UserController {

    public static final String API_USER = "api/user";
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IUserService userService;


   /* @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request){
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }*/

<<<<<<< HEAD
    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername())!= null){
=======
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        if (userService.findUserByUsername(user.getUsername()).isPresent()) {
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(userService.findByEmail(user.getEmail())!= null){
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        //in client side you can sent authroization token value with request header
        //in spring you can get this security value with principal
        if(principal == null) {
            //This should be ok hhtp status because this will be user for logout path
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(jwtTokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

  /*  @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User getUser = user.get();
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

  @Autowired
  public UserMapper userMapper;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "username") String id) {
        return userService.findUserByUsername(id)
                .map(userMapper::toDto)
                .map(userResponse -> new ResponseEntity<>(userResponse, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponse>  findById(@PathVariable(name = "id") Long id) {
//        return userService.findById(id)
//                .map(userMapper::toDto)
//                .map(userResponse -> new ResponseEntity<>(userResponse, HttpStatus.OK))
//                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

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
