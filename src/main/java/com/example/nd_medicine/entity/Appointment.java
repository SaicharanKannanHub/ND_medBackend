package com.example.nd_medicine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer appointmentId;
//    @Column
//    private Long patientId;
//    @Column
//    private Long doctorId;

    @OneToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "DocID")
    private Doctor doctor;

    @OneToOne(targetEntity = Patient.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "patientId")
    private Patient patient;



    @Column
    private LocalTime time;
//    @Column
//    private Date appointmentEndTime;
    @Column
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date appointmentDate;

    public Appointment(Integer l, Doctor doctorTest, Patient patientTest) {
        this.appointmentId = l;
        this.doctor = doctorTest;
        this.patient = patientTest;
    }

    public Integer getId() {
        return appointmentId;
    }

    public void setId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

//    public Long getPatientId() {
//        return patientId;
//    }
//
//    public void setPatientId(Long patientId) {
//        this.patientId = patientId;
//    }
//
//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment() {

    }

    public Appointment(LocalTime time, Date appointmentDate) {
        this.time = time;
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
//    public Date getEndTime() {
//        return appointmentEndTime;
//    }
//    public void setEndTime(Date appointmentEndTime) {
//        this.appointmentEndTime = appointmentEndTime;
//    }
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patient +
                ", doctorId=" + doctor +
                ", appointmentDate=" + appointmentDate +
                ", appointmentStartTime='" + time + '\'' +
//                ", appointmentEndTime='" + appointmentEndTime + '\'' +
                '}';
    }
}
