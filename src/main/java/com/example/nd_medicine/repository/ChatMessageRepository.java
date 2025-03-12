package com.example.nd_medicine.repository;

import com.example.nd_medicine.model.ChatMessage;
import com.example.nd_medicine.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    @Modifying
    @Query("UPDATE ChatMessage chatMessage SET chatMessage.status = :status WHERE chatMessage.senderId = :senderId "
            + "AND chatMessage.recipientId = :recipientId")
    void updateStatusForSenderAndRecipient(
            @Param("senderId") String senderId, @Param("recipientId") String recipientId,
            @Param("status") MessageStatus status);

}
