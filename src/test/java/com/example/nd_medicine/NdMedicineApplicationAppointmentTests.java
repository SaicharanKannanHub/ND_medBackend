package com.example.nd_medicine;

import com.example.nd_medicine.controller.AppointmentController;
import com.example.nd_medicine.entity.*;
import com.example.nd_medicine.repository.AppointmentRepository;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.PatientRepository;
import com.example.nd_medicine.repository.SlotRepository;
import com.example.nd_medicine.service.AppointmentService;
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

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppointmentController.class)
class NdMedicineApplicationAppointmentTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentService appointmentservice;

    @MockBean
    private DoctorRepository d_repository;

    @MockBean
    private PatientRepository p_repository;

    @MockBean
    private AppointmentRepository a_repository;

    @MockBean
    private SlotRepository s_repository;

    @Test
    public void givenValidAppointment_whenBookingAppointment_thenReturnAppointmentDetails() throws Exception {

        Doctor doctorTest = new Doctor("saiDoc", "kannan", "sai@gmail");
        Patient patientTest = new Patient("saiPatient", "kannan", "sai@gmail");

        Appointment testAppointment = new Appointment(5, doctorTest, patientTest);

        given(appointmentservice.create(Mockito.any())).willReturn(testAppointment);

        mvc.perform(post("/appointment/createAppointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testAppointment)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.doctor.email", CoreMatchers.is(doctorTest.getEmail())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.patient.firstname", CoreMatchers.is(patientTest.getFirstname())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id", CoreMatchers.is(testAppointment.getId())));

        verify(appointmentservice, VerificationModeFactory.times(1)).create(any());
        reset(appointmentservice);

    }

    @Test
    public void givenAppointmentId_whenRequestingAppointmentById_thenReturnAppointmentDetails() throws Exception {

        Doctor doctorTest = new Doctor("saiDoc", "kannan", "sai@gmail");
        Patient patientTest = new Patient("saiPatient", "kannan", "sai@gmail");

        Appointment testAppointment = new Appointment(5, doctorTest, patientTest);

        given(appointmentservice.findAppointmentByAppointmentId(Mockito.any())).willReturn(testAppointment);

        mvc.perform(get("/appointment/5"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.doctor.email", CoreMatchers.is(doctorTest.getEmail())));

        verify(appointmentservice, VerificationModeFactory.times(1)).findAppointmentByAppointmentId(any());
        reset(appointmentservice);

    }
    @Test
    public void givenIncorrectAppointmentId_whenRequestingAppointmentById_thenReturnEmptyAppointment() throws Exception {

        Doctor doctorTest = new Doctor("saiDoc", "kannan", "sai@gmail");
        Patient patientTest = new Patient("saiPatient", "kannan", "sai@gmail");

        Appointment testAppointment = new Appointment(5, doctorTest, patientTest);
        Appointment emptyAppointment = new Appointment();

        a_repository.saveAndFlush(testAppointment);

        given(appointmentservice.findAppointmentByAppointmentId(Mockito.any())).willReturn(emptyAppointment);

        mvc.perform(get("/appointment/10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id", CoreMatchers.is(emptyAppointment.getId())));

        verify(appointmentservice, VerificationModeFactory.times(1)).findAppointmentByAppointmentId(any());
        reset(appointmentservice);

    }


}