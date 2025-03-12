package com.example.nd_medicine.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
public class Slot {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "slotId")
    private int slotId;


    @OneToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "DocID")
    private Doctor doctor;

    @Column(name = "slotTime")
    private LocalTime slotTime;

    @Column(name = "booked")
    private boolean booked;

    public Slot(Doctor doctor, LocalTime slotTime, boolean booked) {
        this.doctor = doctor;
        this.slotTime = slotTime;
        this.booked = booked;
    }

    public Slot() {
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalTime getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(LocalTime slotTime) {
        this.slotTime = slotTime;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
