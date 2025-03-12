package com.example.nd_medicine;

import com.example.nd_medicine.controller.FileDbController;
import com.example.nd_medicine.repository.FileDbRepository;
import com.example.nd_medicine.service.FileDbService;
import com.example.nd_medicine.uploadedfiles.FileDb;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FileDbController.class)
class NdMedicineApplicationTestsFileDbController {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileDbRepository f_repository;

    @MockBean
    private FileDbService filedbservice;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void resetDb() {

    }

    @Test
    public void testFileUpload() throws Exception {

        MultipartFile testfile = new MockMultipartFile("file",
                "fileThatDoesNotExists.txt",
                "text/plain",
                "This is a dummy file content".getBytes(StandardCharsets.UTF_8));

        FileDb fileDb = new FileDb(UUID.randomUUID().toString(), testfile.getOriginalFilename(), testfile.getContentType(), testfile.getBytes());

        filedbservice.store(testfile);
        f_repository.saveAndFlush(fileDb);

        String content = filedbservice.getFileList().toString();

        System.out.println("Content: " + content);

    }

    @Test
    public void testUploadFile() throws Exception {

        MultipartFile mockMultipartFile = new MockMultipartFile("file",
                "fileThatDoesNotExists.txt",
                "application/vnd.ms-excel",
                "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
        MvcResult result = mvc.perform(MockMvcRequestBuilders.multipart("/file").file((MockMultipartFile) mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertNotNull(result.getResponse().getContentAsString());

        System.out.println("File List: " + filedbservice.getFileList().toString());

    }

}