package com.example.nd_medicine;

import com.example.nd_medicine.controller.FileDbController;
import com.example.nd_medicine.controller.UserController;
import com.example.nd_medicine.entity.User;
import com.example.nd_medicine.repository.DoctorRepository;
import com.example.nd_medicine.repository.FileDbRepository;
import com.example.nd_medicine.repository.PatientRepository;
import com.example.nd_medicine.repository.UserRepository;
import com.example.nd_medicine.security.LoginResponse;
import com.example.nd_medicine.security.Response;
import com.example.nd_medicine.service.AuthenticationService;
import com.example.nd_medicine.service.FileDbService;
import com.example.nd_medicine.service.UserService;
import com.example.nd_medicine.uploadedfiles.FileDb;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FileDbController.class)

class NdMedicineFileUploadTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileDbRepository fdb_repository;

    @MockBean
    private FileDbService fdb_service;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void resetDb() {
        fdb_repository.deleteAll();
    }

    @Test
    public void givenFile_whenUploadingValidFile_thenReceiveNewFile() throws Exception {

        String fileName = "sample_txt_file";
        MockMultipartFile testFile = new MockMultipartFile(
                "file",
                fileName,
                "text/plain",
                "Placeholder for Patient Prescription".getBytes());

        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/file");

        mvc.perform(multipartRequest.file(testFile))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenNoFile_whenAttemptingFileUpload_thenThrowBadRequestError() throws Exception{

        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/file");

        mvc.perform(multipartRequest)
                .andExpect(status().isBadRequest());

    }

    @Test
    public void givenFile_whenUploadingValidFile_thenCheckIfBytesReceivedAreAsExpected() throws Exception {

        String fileName = "sample_txt_file";
        MockMultipartFile testFile = new MockMultipartFile(
                "file",
                fileName,
                "text/plain",
                "Placeholder for Patient Prescription".getBytes());

        FileDb filedb = new FileDb("7", "file", "f", testFile.getBytes());

        given(fdb_service.store(any())).willReturn(filedb);

// Not sure how to do this?

//        mvc.perform(post("/file")
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .content(String.valueOf(testFile)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers
//                        .jsonPath("$.status", CoreMatchers.is("Failed")));

        System.out.println("Printing");
        System.out.println(fdb_service.getFileList());

    }

}
