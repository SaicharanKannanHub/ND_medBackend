package com.example.nd_medicine.repository;

import com.example.nd_medicine.entity.AuthenticationLogin;
import com.example.nd_medicine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//repo for authentication
@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationLogin,Integer> {

    AuthenticationLogin findByUser(User user);
}
