package com.example.nd_medicine.repository;

import com.example.nd_medicine.entity.Doctor;
import com.example.nd_medicine.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
//    Patient findbyPatientId(Integer id);
//    List<Patient> findPatientByEmail(String email);


}