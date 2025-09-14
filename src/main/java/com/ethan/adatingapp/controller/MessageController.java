package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.Chat;
import com.ethan.adatingapp.domain.Message;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.repository.ChatRepository;
import com.ethan.adatingapp.repository.UserRepository;
import com.ethan.adatingapp.service.MessageService;
import com.ethan.adatingapp.util.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:8081")
public class MessageController {

    private final MessageService messageService;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public MessageController(MessageService messageService,
                             ChatRepository chatRepository,
                             UserRepository userRepository) {
        this.messageService = messageService;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    // Send a message
    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendMessage(
            @RequestParam Long chatId,
            @RequestParam Long senderId,
            @RequestParam String content) {

        Chat chat = chatRepository.findById(chatId).orElse(null);
        User sender = userRepository.findById(senderId).orElse(null);

        if (chat == null || sender == null) {
            return ResponseEntity.badRequest().build();
        }

        Message message = messageService.sendMessage(chat, sender, content);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    // Fetch messages for a chat
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageDTO>> getMessagesForChat(@PathVariable Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElse(null);

        if (chat == null) {
            return ResponseEntity.notFound().build();
        }

        List<MessageDTO> messages = messageService.getMessagesForChat(chat)
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(messages);
    }
}
