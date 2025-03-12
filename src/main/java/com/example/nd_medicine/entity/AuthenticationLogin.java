package com.example.nd_medicine.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "authentication")
public class AuthenticationLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    private String token;

    //entity with a one to one reletion with the User class where it gets the user_id
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public AuthenticationLogin(int id, String token, User user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public AuthenticationLogin() {
    }

    public AuthenticationLogin(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
