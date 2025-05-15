package com.sdh2.demo.service;

import com.sdh2.demo.model.User;
import com.sdh2.demo.repositary.UserRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(Long id, User newUser) {
        userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    userRepository.save(user);
                    return ResponseEntity.ok(user);
                }).orElse(ResponseEntity.notFound().build());

    }

   public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Page getUsers(int page, int size) {
        return (Page) userRepository.findAll(PageRequest.of(page, size));
    }
}
