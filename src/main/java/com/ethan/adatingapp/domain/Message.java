package com.ethan.adatingapp.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime sentAt = LocalDateTime.now();


    // Constructors
    public Message() {}

    public Message(Chat chat, User sender, String content) {
        this.chat = chat;
        this.sender = sender;
        this.content = content;
    }

    // Getters & Setters
    public Long getMessageId() { return messageId; }
    public Chat getChat() { return chat; }
    public void setChat(Chat chat) { this.chat = chat; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
