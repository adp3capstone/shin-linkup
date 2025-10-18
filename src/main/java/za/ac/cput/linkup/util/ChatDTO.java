package za.ac.cput.linkup.util;

import za.ac.cput.linkup.domain.Chat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ChatDTO {
    private Long chatId;
    private Long matchId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MessageDTO> messages;

    public ChatDTO(Chat chat) {
        if (chat != null) {
            this.chatId = chat.getChatId();
            this.matchId = chat.getMatch() != null ? chat.getMatch().getMatchId() : null;
            this.createdAt = chat.getCreatedAt();
            this.updatedAt = chat.getUpdatedAt();
            this.messages = chat.getMessages() != null
                    ? chat.getMessages().stream().map(MessageDTO::new).collect(Collectors.toList())
                    : null;
        }
    }

    public Long getChatId() {
        return chatId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }
}
