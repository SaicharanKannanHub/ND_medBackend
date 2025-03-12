//package com.example.nd_medicine.controller;
//
//import com.example.nd_medicine.service.FileUploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//public class FileUploadController {
//
//    @Autowired
//    FileUploadService fileUploadService;
//
//    @PostMapping
//    public void uploadFile(@RequestParam("file") MultipartFile file ) throws IOException {
//
//        fileUploadService.uploadFile(file);
//
//    }
//
//}
