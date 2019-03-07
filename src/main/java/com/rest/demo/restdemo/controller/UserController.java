package com.rest.demo.restdemo.controller;

import com.rest.demo.restdemo.model.User;
import com.rest.demo.restdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    

}
