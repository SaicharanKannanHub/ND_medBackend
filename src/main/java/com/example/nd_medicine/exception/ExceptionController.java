package com.example.nd_medicine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//controller that deals with errors
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handlesCustomException(CustomException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AuthenticationFailException.class)
    public final  ResponseEntity<String> handlesAuthenticationException(AuthenticationFailException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }
}

