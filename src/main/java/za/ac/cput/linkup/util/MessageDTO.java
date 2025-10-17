package za.ac.cput.linkup.util;

import za.ac.cput.linkup.domain.Message;

import java.time.LocalDateTime;

public class MessageDTO {
    private Long messageId;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;

    public MessageDTO(Message message) {
        if (message != null) {
            this.messageId = message.getMessageId();
            this.senderId = message.getSender() != null ? message.getSender().getUserId() : null;
            this.content = message.getContent();
            this.sentAt = message.getSentAt();
        }
    }

    public Long getMessageId() {
        return messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
