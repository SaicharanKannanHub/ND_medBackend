package com.example.nd_medicine.service;

import com.example.nd_medicine.entity.AuthenticationLogin;
import com.example.nd_medicine.entity.User;
import com.example.nd_medicine.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationRepository authenticationRepository;

    //saves an authenticaiton with token -- will be used in later sprints
    public void saveAuthentication(AuthenticationLogin authenticationLogin) {
        authenticationRepository.save(authenticationLogin);

    }

    //returns token

    public AuthenticationLogin getToken(User user) {
        return authenticationRepository.findByUser(user);
    }
}
