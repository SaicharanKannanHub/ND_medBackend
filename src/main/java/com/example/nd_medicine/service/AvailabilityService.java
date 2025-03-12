package com.example.nd_medicine.service;

import com.example.nd_medicine.entity.Availability;
import com.example.nd_medicine.entity.Doctor;
import com.example.nd_medicine.entity.Slot;
import com.example.nd_medicine.repository.AvailabilityRepository;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;

    private final SlotRepository slotRepository;

    @Autowired
    SlotService slotService;

    @Autowired
    DoctorRepository doctorRepository;


//    @Autowired
//    SlotRepository slotRepository;
//    SlotService slotService;


    public AvailabilityService(AvailabilityRepository availabilityRepository, SlotRepository slotRepository) {
        this.availabilityRepository = availabilityRepository;

        this.slotRepository = slotRepository;
    }

    public Availability saveDoctorAvailability(Availability availability) {

        slotService.initiateSlotsIntoDb(availability.getDoctor(),availability.getStartTime(),availability.getEndTime());

        return availabilityRepository.save(availability);


    }

    public List<Availability> findAvailabilityByDoctorId(Integer id) {
        return availabilityRepository.findAvailabilitiesByDoctorId(id);
    }



//    @Transactional
//    public Slot testing() {
//        slotService.initiateSlotsIntoDb();
//        return null;
//    }
}
