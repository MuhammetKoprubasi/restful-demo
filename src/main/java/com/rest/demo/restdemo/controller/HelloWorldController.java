package com.rest.demo.restdemo.controller;

import com.rest.demo.restdemo.dto.HelloWorldDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String getHello(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-dto")
    public HelloWorldDTO getHelloWorldDTO(){
        return new HelloWorldDTO("Hello World");
    }

    @GetMapping(path = "/hello-world-dto/{name}")
    public HelloWorldDTO getHelloWorldDTO(@PathVariable String name){
        return new HelloWorldDTO(name);
    }
}
