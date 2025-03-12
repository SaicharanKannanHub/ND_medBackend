package com.example.nd_medicine.controller;

import com.example.nd_medicine.security.Login;
import com.example.nd_medicine.security.LoginResponse;
import com.example.nd_medicine.security.Response;
import com.example.nd_medicine.security.SignUpResponse;
import com.example.nd_medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    //sign-up
    @PostMapping("/signup")
    public Response signup(@RequestBody SignUpResponse signUpResponse){
        return userService.signup(signUpResponse);
    }

    //creates a doctor
    @PostMapping("/signup/doctor")
    public Response signupDoctor(@RequestBody SignUpResponse signUpResponse){
        return userService.signupDoctor(signUpResponse);
    }
    //creates a patient
    @PostMapping("/signup/patient")
    public Response signupPatients(@RequestBody SignUpResponse signUpResponse){
        return userService.signupPatient(signUpResponse);
    }

    //login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody Login login){
        return userService.logIn(login);

    }
}
