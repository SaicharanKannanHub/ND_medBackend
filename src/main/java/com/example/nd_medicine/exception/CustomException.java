package com.example.nd_medicine.exception;

//to show errors
public class CustomException extends IllegalArgumentException{

    public CustomException(String message){
        super(message);
    }

}
