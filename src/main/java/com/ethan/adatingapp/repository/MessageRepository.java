package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.Chat;
import com.ethan.adatingapp.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByChatOrderBySentAtAsc(Chat chat);
}
