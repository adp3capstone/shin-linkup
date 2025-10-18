package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.Chat;
import za.ac.cput.linkup.domain.Message;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.repository.MessageRepository;
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
