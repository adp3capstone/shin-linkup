package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.Chat;
import com.ethan.adatingapp.domain.Message;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Send a message
    public Message sendMessage(Chat chat, User sender, String content) {
        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(content);
        message.setSentAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Fetch messages for a chat
    public List<Message> getMessagesForChat(Chat chat) {
        return messageRepository.findByChatOrderBySentAtAsc(chat);
    }
}
