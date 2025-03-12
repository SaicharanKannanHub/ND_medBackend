package com.example.nd_medicine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nd_medicine.uploadedfiles.FileDb;

@Repository
public interface FileDbRepository extends JpaRepository<FileDb, String> {



}
