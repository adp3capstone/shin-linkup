package za.ac.cput.linkup.controller;

import za.ac.cput.linkup.domain.Chat;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.service.ChatService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "http://localhost:8081")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    // Get all chats for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatDTO>> getChatsForUser(@PathVariable Long userId) {
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Chat> chats = chatService.getChatsForUser(user);
        List<ChatDTO> dtos = chats.stream().map(ChatDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Get chat by ID
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDTO> getChatById(@PathVariable Long chatId) {
        Chat chat = chatService.getChat(chatId);
        if (chat == null) {
            return ResponseEntity.notFound().build();
        }
        ChatDTO dto = new ChatDTO(chat);
        return ResponseEntity.ok(dto);
    }

    // Create a new chat (associates with a match)
    @PostMapping("/create/bymatch/{matchId}")
    public ResponseEntity<ChatDTO> createChat(@PathVariable Long matchId) {
        Chat chat = chatService.createWithMatch(matchId);
        if (chat != null) {
            ChatDTO dto = new ChatDTO(chat);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }

}
