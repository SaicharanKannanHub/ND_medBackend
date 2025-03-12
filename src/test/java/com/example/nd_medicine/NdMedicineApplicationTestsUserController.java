package com.example.nd_medicine;

import com.example.nd_medicine.controller.UserController;
import com.example.nd_medicine.entity.User;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.PatientRepository;
import com.example.nd_medicine.repository.UserRepository;
import com.example.nd_medicine.security.LoginResponse;
import com.example.nd_medicine.security.Response;
import com.example.nd_medicine.service.AuthenticationService;
import com.example.nd_medicine.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
class NdMedicineApplicationTestsUserController {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientRepository p_repository;

    @MockBean
    private DoctorRepository d_repository;

    @MockBean
    private UserRepository u_repository;

    @MockBean
    private UserService userservice;

    @MockBean
    private AuthenticationService authenticationservice;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void resetDb() {
        p_repository.deleteAll();
        d_repository.deleteAll();
    }

    @Test
    public void givenNewPatient_whenSigningUp_thenAddNewPatientSuccess() throws Exception {

        User testUser = new User("Debbie", "Lou", "debbielou@gmail.com", "debbiespassword");
        Response response = new Response("Success","Patient Created");
        given(userservice.signupPatient(any())).willReturn(response);

        mvc.perform(post("/user/signup/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Success")));
        verify(userservice, VerificationModeFactory.times(1)).signupPatient(any());
        reset(userservice);

    }

    @Test
    public void givenNewPatientWithoutPassword_whenSigningUp_thenAddNewPatientFail() throws Exception {

        User testUser = new User("Debbie", "Lou", "debbielou@gmail.com", "");
        Response response = new Response("Failed","");
        given(userservice.signupPatient(any())).willReturn(response);

        mvc.perform(post("/user/signup/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Failed")));
        verify(userservice, VerificationModeFactory.times(1)).signupPatient(any());
        reset(userservice);

    }

    @Test
    public void givenDuplicatePatientEmail_whenSigningUp_thenAddNewPatientFail() throws Exception {

        User testUser = new User("Debbie", "Lou", "debbielou@gmail.com", "password123");
        User testUserDuplicateEmail = new User("Debbie'sEvilTwin", "Lou", "debbielou@gmail.com", "password123");

        Response response = new Response("Failed","Patient Already Exists");

        u_repository.saveAndFlush(testUser);

        given(userservice.signupPatient(any())).willReturn(response);

        mvc.perform(post("/user/signup/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUserDuplicateEmail)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Failed")));
        verify(userservice, VerificationModeFactory.times(1)).signupPatient(any());
        reset(userservice);

    }

    @Test
    public void givenNewDoctor_whenSigningUp_thenAddNewDoctorSuccess() throws Exception {

        User testDoctor = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "password123");
        Response response = new Response("Success","Doctor Created");
        given(userservice.signupDoctor(any())).willReturn(response);

        mvc.perform(post("/user/signup/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testDoctor)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Success")));
        verify(userservice, VerificationModeFactory.times(1)).signupDoctor(any());
        reset(userservice);

    }

    @Test
    public void givenDuplicateDoctorEmail_whenSigningUp_thenAddNewDoctorFail() throws Exception {

        User testUser = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "password123");
        User testUserDuplicate = new User("LizaV2ElectricBoogaloo", "Liza's Last Name", "lizasemailaddress@gmail.com", "password123");

        Response response = new Response("Failed","Doctor Already Exists");

        u_repository.saveAndFlush(testUser);

        given(userservice.signupDoctor(any())).willReturn(response);

        mvc.perform(post("/user/signup/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUserDuplicate)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Failed")));
        verify(userservice, VerificationModeFactory.times(1)).signupDoctor(any());
        reset(userservice);

    }

    @Test
    public void givenCorrectPassword_whenLoggingIn_thenLoginSuccess() throws Exception {

        User testUser = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "password123");

        LoginResponse response = new LoginResponse("Success","password123");

        u_repository.saveAndFlush(testUser);

        given(userservice.logIn(any())).willReturn(response);

        mvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Success")));
        verify(userservice, VerificationModeFactory.times(1)).logIn(any());
        reset(userservice);

    }

    @Test
    public void givenIncorrectPassword_whenLoggingIn_thenLoginFail() throws Exception {

        User testUser = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "password123");
        User testUserAttemptedLogin = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "password1234");

        LoginResponse response = new LoginResponse("Failed","password123");

        u_repository.saveAndFlush(testUserAttemptedLogin);

        given(userservice.logIn(any())).willReturn(response);

        mvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Failed")));
        verify(userservice, VerificationModeFactory.times(1)).logIn(any());
        reset(userservice);

    }

    @Test
    public void givenBlankPassword_whenLoggingIn_thenLoginFail() throws Exception {

        User testUser = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "password123");
        User testUserAttemptedLogin = new User("Liza", "Liza's Last Name", "lizasemailaddress@gmail.com", "");

        LoginResponse response = new LoginResponse("Failed","password123");

        u_repository.saveAndFlush(testUserAttemptedLogin);

        given(userservice.logIn(any())).willReturn(response);

        mvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.status", CoreMatchers.is("Failed")));
        verify(userservice, VerificationModeFactory.times(1)).logIn(any());
        reset(userservice);

    }

    @Test
    public void givenPassword_whenGeneratingHash_thenGenerateCorrectMD5Hash() throws Exception {


        String password = "password123";
        String hashedPassword = hashPassword(password);

        // Expected hash obtained from external source
        // Source: https://www.md5hashgenerator.com/
        String expectedHash = "482C811DA5D5B4BC6D497FFA98491E38";

        assertEquals(expectedHash, hashedPassword);

        System.out.println("Actual Hash: " + hashedPassword);
        System.out.println("Expected Hash: " + expectedHash);

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

}