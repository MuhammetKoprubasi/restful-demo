package com.rest.demo.restdemo.controller;

import com.rest.demo.restdemo.exception.UserNotFoundException;
import com.rest.demo.restdemo.model.Post;
import com.rest.demo.restdemo.model.User;
import com.rest.demo.restdemo.repository.PostRepository;
import com.rest.demo.restdemo.repository.UserRepository;
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
import java.util.Optional;

@RestController
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> getUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        Resource<User> resource = new Resource<>(user.get());

        Link link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users");

        resource.add(link);

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> save(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping(path ="/jpa/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        userRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> getPost(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent())
            throw new UserNotFoundException("id:" + id);

        return userOptional.get().getPost();
    }


    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> savePost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.orElseThrow(() -> new UserNotFoundException("id:" + id));

        post.setUser(user);

        Post post1 = postRepository.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



}
