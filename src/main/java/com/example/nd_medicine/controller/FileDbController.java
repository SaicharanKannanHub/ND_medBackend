package com.example.nd_medicine.controller;

import com.example.nd_medicine.service.FileDbService;
import com.example.nd_medicine.uploadedfiles.FileDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileDbController {

    @Autowired
    private FileDbService fileDbService;

    @PostMapping
    public FileDb uploadFile(@RequestParam("file")MultipartFile file) throws IOException {

        return fileDbService.store(file);

    }

    @GetMapping("/{id}")
    public FileDb getFile(@PathVariable String id) {

        return fileDbService.getFileById(id);

    }

    @GetMapping("/list")
    public List<FileDb> getFileList() {

        return fileDbService.getFileList();

    }

}
