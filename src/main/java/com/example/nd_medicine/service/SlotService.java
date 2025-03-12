package com.example.nd_medicine.service;

import com.example.nd_medicine.entity.AuthenticationLogin;
import com.example.nd_medicine.entity.Availability;
import com.example.nd_medicine.entity.Doctor;
import com.example.nd_medicine.entity.Slot;
import com.example.nd_medicine.repository.AuthenticationRepository;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlotService {

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public List<LocalTime> slotCreater() {
        int gapInMinutes = 30;  // Define your span-of-time.
        int loops = ((int) Duration.ofHours(24).toMinutes() / gapInMinutes);
        List<LocalTime> times = new ArrayList<>(loops);

        LocalTime time = LocalTime.MIN;  // '00:00'
        for (int i = 1; i <= loops; i++) {
            times.add(time);
            // Set up next loop.
            time = time.plusMinutes(gapInMinutes);
        }


        //testing purpsoses
//        System.out.println(times);

        return times;
    }



    public void saveSlot(Slot slot) {
        slotRepository.save(slot);

    }

    public void initiateSlotsIntoDb(Doctor doctor,LocalTime startTime, LocalTime endTime){

        //create slots
        List<LocalTime> times = slotCreater();
        List<LocalTime> timeFinal = new ArrayList<>();
        int lowerBound = times.indexOf(startTime);
        int upperBound = times.indexOf(endTime);

//        System.out.println(lowerBound + " " + upperBound);

        for (int i = lowerBound ; i < upperBound+1; i++){
            timeFinal.add(times.get(i));
        }
        System.out.println(timeFinal);



        //test
//        Doctor doctorTest = new Doctor("sai", "kannan", "sai@gmail");
//        Doctor doctorTest2 = new Doctor("saitest2", "kannantest2", "sai@gmailtest2");
//        doctorRepository.save(doctorTest);
//        doctorRepository.save(doctorTest2);

        for (int i = 0 ; i < timeFinal.size(); i++){
            //test
            Slot slot = new Slot();
//            Slot slot2 = new Slot();
            slot.setDoctor(doctor);
            slot.setSlotTime(timeFinal.get(i));

//            slot2.setDoctor(doctorTest2);
//            slot2.setSlotTime(timeFinal.get(i));


            saveSlot(slot);
//            saveSlot(slot2);




        }
    }
}

