package com.example.nd_medicine.service;

import com.example.nd_medicine.repository.FileDbRepository;
import com.example.nd_medicine.uploadedfiles.FileDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileDbService {

    @Autowired
    private FileDbRepository fileDbRepository;

    public FileDb store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        FileDb fileDb = new FileDb(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes());
        return fileDbRepository.save(fileDb);
    }

    public FileDb getFileById(String id) {

        Optional<FileDb> fileOptional = fileDbRepository.findById(id);

        if (fileOptional.isPresent()) {

            return fileOptional.get();

        }
        return null;

    }

    public List<FileDb> getFileList() {

        return fileDbRepository.findAll();

    }

}
