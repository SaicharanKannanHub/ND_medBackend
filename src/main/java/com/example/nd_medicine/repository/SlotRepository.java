package com.example.nd_medicine.repository;

import com.example.nd_medicine.entity.Appointment;
import com.example.nd_medicine.entity.Doctor;
import com.example.nd_medicine.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    Slot findByDoctor(Doctor doctor);


    @Query("SELECT t FROM Slot t WHERE t.doctor=?1 AND t.booked= false ")
    List<Slot> findSlotsByDoctorId(Doctor doctor);


}

