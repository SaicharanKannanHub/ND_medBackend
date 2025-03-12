package com.example.nd_medicine.controller;

import com.example.nd_medicine.entity.Appointment;
import com.example.nd_medicine.entity.Doctor;
import com.example.nd_medicine.entity.Patient;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.PatientRepository;
import com.example.nd_medicine.repository.SlotRepository;
import com.example.nd_medicine.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    SlotRepository slotRepository;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //new appointment request
    @PostMapping("/createAppointment")
    public Appointment createAppointment(@RequestBody Appointment newAppointment) {

        // needs to be updated to pull the time from the selected available time// front end work as well


        //doctor and patient need to be pulled from the current user.


        Doctor doctorTest = new Doctor("saiDoc", "kannan", "sai@gmail");
        Patient patienttest = new Patient("saiPatient", "kannan", "sai@gmail");

        doctorRepository.save(doctorTest);
        patientRepository.save(patienttest);



        newAppointment.setDoctor(doctorTest);
        newAppointment.setPatient(patienttest);
//        newAppointment.


        return appointmentService.create(newAppointment);

    }

    //getting a particular appointment by id useful in admin maybe
    @GetMapping(value = "/{appointmentId}")
    public Appointment viewAppointmentById(@PathVariable Integer appointmentId){
        return appointmentService.findAppointmentByAppointmentId(appointmentId);
    }

    //all appointments for a particular patient -- useful in patient profile
    @GetMapping(value = "/patient/{patientId}")
    public List<Appointment> viewAppointmentOfPatient(@PathVariable Integer patientId){
        return appointmentService.findByPatientId(patientId);
    }

    //all appointments for a particular patient -- useful in doc profile
    @GetMapping(value = "/doctor/{doctorId}")
    public List<Appointment> viewAppointmentOfDoctor(@PathVariable Integer doctorId){
        return appointmentService.findByDoctorId(doctorId);
    }


}
