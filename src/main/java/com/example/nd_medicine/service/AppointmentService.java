package com.example.nd_medicine.service;

import com.example.nd_medicine.entity.Appointment;
import com.example.nd_medicine.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment create(Appointment newAppointment) {
        return appointmentRepository.save(newAppointment);
    }
    public List<Appointment> findByDoctorId(Integer doctorId) {
        return appointmentRepository.findAppointmentsByDoctorId(doctorId);
    }

    public List<Appointment> findByPatientId(Integer patientId) {
        return appointmentRepository.findAppointmentsByPatientId(patientId);
    }

    public Appointment findAppointmentByAppointmentId(Integer appointmentId) {
        return appointmentRepository.findAppointmentByAppointmentId(appointmentId);
    }
}
