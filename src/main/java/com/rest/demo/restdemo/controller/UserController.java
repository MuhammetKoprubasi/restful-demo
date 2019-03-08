package com.rest.demo.restdemo.controller;

import com.rest.demo.restdemo.model.User;
import com.rest.demo.restdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public Resource<User> getUser(@PathVariable int id){
        User user = userService.getUserById(id);

        Resource<User> resource = new Resource<>(user);

        Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users");

        resource.add(link);

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> save(@Valid @RequestBody User user){
        User savedUser = userService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping(path ="/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        boolean deleted = userService.deleteById(id);

        return ResponseEntity.ok().build();
    }



}
