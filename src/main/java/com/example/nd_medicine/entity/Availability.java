package com.example.nd_medicine.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "DOCTORAVAILABILTIES")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TableID")
    private Integer availId;

//    @Column(name = "DocID")
//    private Long doctorId;

    @OneToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "DocID")
    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Column(name = "AvailableFrom")
    private LocalTime startTime;

    @Column(name = "AvailableTo")
    private LocalTime endTime;

//    @Column(name = "Booked")
//    private Boolean booked;

    public Availability() {
    }

    public Availability(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getAvailId() {
        return availId;
    }

    public void setAvailId(Integer id) {
        this.availId = id;
    }

//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
///to do booked boolean
//    public Boolean getBooked() {
//        return booked;
//    }
//
//    public void setBooked(Boolean booked) {
//        this.booked = booked;
//    }

    @Override
    public String toString() {
        return "AvailabilityTime{" +
                "availid=" + availId +
                ", doctorId=" + doctor +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}