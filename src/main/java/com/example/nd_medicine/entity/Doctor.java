package com.example.nd_medicine.entity;

import javax.persistence.*;

@Entity
@Table(name="Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocID")
    private Integer id;

    @Column(name = "FirstName")
    private String firstname;

    @Column(name = "LastName")
    private String lastname;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;

    @Column(name = "Specialise")
    private String specialise;


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getSpecialise() { return specialise; }

    public void setSpecialise(String specialise) { this.specialise = specialise; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Doctor(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email ='" + email + '\'' +
                ", phone=" + phone +
                ", specialise='" + specialise + '\'' +
                '}';
    }
}