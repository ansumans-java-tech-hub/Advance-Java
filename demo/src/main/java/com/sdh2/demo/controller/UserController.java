package com.sdh2.demo.controller;

import com.sdh2.demo.exception.NoUsersFoundException;
import com.sdh2.demo.model.User;
import com.sdh2.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class UserController {

    RestTemplate restTemplate = new RestTemplate();

    private final UserService userService  ;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Retrieve user by ID
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() throws Exception{
        List<User> users= userService.findAllUsers();
        if(users.isEmpty()) {
            log.error("No Users Found");
            throw new NoUsersFoundException("No Users Found");
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/page")
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return (Page<User>) userService.getUsers(page, size);
    }


}


  /*  // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    userRepo.save(user);
                    return ResponseEntity.ok(user);
                }).orElse(ResponseEntity.notFound().build());
    } */



