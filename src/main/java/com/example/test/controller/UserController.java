package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/test/{test}")
    public String test(@PathVariable String test) {
        return test;
    }

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable String name) {
        User user =  userRepository.findUserByUserName(name);
        System.out.println(user);
        return user;
    }

    @PostMapping("/user/{id}/{name}/{password}")
    public String createUser(@PathVariable Long id, @PathVariable String name, @PathVariable String password) {
        User user = new User(id, name, password);
        userRepository.saveUser(user);
        return "done";
    }
}
