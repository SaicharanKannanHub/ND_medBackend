package com.example.nd_medicine.repository;

import com.example.nd_medicine.model.ChatNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatNotificationRepository extends JpaRepository<ChatNotification, String> {

    Optional<ChatNotification> findById(String id);

}
