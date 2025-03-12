package com.example.nd_medicine.controller;

import com.example.nd_medicine.entity.Availability;
import com.example.nd_medicine.entity.Doctor;
import com.example.nd_medicine.entity.Slot;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.SlotRepository;
import com.example.nd_medicine.service.AvailabilityService;
//import com.example.nd_medicine.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class AvailabilityController {
    private final AvailabilityService availabilityService;
    ArrayList<Doctor> currentlyUsing = new ArrayList<Doctor>();




    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    SlotRepository slotRepository;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    //doctor adds availability
    @PostMapping(value = "/availability")
    public Availability createAvailability(@RequestBody Availability availability){
        Doctor doctorTest = new Doctor("sai", "kannan", "sai@gmail");

        doctorRepository.save(doctorTest);
        currentlyUsing.add(doctorTest);
// needs to be updated to get the current user details thats why i needed all the files.
        availability.setDoctor(doctorTest);
        return availabilityService.saveDoctorAvailability(availability);
    }


    ///testing


    //viewing doctor availability by doctor id - search and admin view
    @GetMapping(value = "/availability/{id}")
    public List<Slot> getAvailabilityByDoctorId(@PathVariable Long id){



        return  slotRepository.findSlotsByDoctorId(currentlyUsing.get(0));


    }



    ///testttting function
//    @PostMapping(value = "/availability/slot")
//    public Slot test(){
//        return availabilityService.testing();
//    }

}
