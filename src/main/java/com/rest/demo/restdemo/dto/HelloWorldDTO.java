package com.rest.demo.restdemo.dto;

public class HelloWorldDTO {
    private String message;

    public HelloWorldDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldDTO{" +
                "message='" + message + '\'' +
                '}';
    }
}
