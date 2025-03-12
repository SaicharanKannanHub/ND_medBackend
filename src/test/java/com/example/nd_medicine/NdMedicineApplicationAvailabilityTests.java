package com.example.nd_medicine;

import com.example.nd_medicine.controller.AvailabilityController;
import com.example.nd_medicine.entity.*;
import com.example.nd_medicine.repository.AppointmentRepository;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.PatientRepository;
import com.example.nd_medicine.repository.SlotRepository;
import com.example.nd_medicine.service.AvailabilityService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AvailabilityController.class)
class NdMedicineApplicationAvailabilityTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AvailabilityService availabilityservice;

    @MockBean
    private DoctorRepository d_repository;

    @MockBean
    private PatientRepository p_repository;

    @MockBean
    private AppointmentRepository a_repository;

    @MockBean
    private SlotRepository s_repository;

    @Test
    public void givenAvailability_whenCreatingAvailability_thenReturnCreatedAvailability() throws Exception {

        Doctor doctorTest = new Doctor("saiDoc", "kannan", "sai@gmail");

        LocalTime doctorStartTest = LocalTime.of(9, 00, 00);
        LocalTime doctorEndTest = LocalTime.of(13, 00, 00);

        Availability testAvailability = new Availability();
        testAvailability.setDoctor(doctorTest);
        testAvailability.setAvailId(10);

        given(availabilityservice.saveDoctorAvailability(Mockito.any())).willReturn(testAvailability);

        mvc.perform(post("/doctor/availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testAvailability)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.availId", CoreMatchers.is(testAvailability.getAvailId())));


        verify(availabilityservice, VerificationModeFactory.times(1)).saveDoctorAvailability(any());
        reset(availabilityservice);

    }

}