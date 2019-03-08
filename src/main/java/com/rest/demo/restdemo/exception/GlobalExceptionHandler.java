package com.rest.demo.restdemo.exception;

import com.rest.demo.restdemo.dto.ExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest wr){
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), ex.getMessage(),wr.getDescription(false));

        return new ResponseEntity(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest wr){
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), ex.getMessage(),wr.getDescription(false));

        return new ResponseEntity(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(), ex.getBindingResult().toString(),request.getDescription(false));
        return new ResponseEntity(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
