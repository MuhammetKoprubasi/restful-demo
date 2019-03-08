package com.rest.demo.restdemo.controller;

import com.rest.demo.restdemo.dto.HelloWorldDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping("/morning")
    public String morning(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
