package com.example.nd_medicine.exception;

//exception to class to show errors
public class AuthenticationFailException extends IllegalArgumentException{

    public AuthenticationFailException(String message){
        super(message);
    }
}
