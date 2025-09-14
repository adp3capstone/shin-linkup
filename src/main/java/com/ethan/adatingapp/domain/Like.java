package com.ethan.adatingapp.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"liker_id", "liked_id"}))
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who likes another user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liker_id", nullable = false)
    private User liker;

    // The user who is liked
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liked_id", nullable = false)
    private User liked;

    // Optional: when the like was made
    private LocalDateTime likedAt = LocalDateTime.now();

    public Like() {}

    public Like(User liker, User liked) {
        this.liker = liker;
        this.liked = liked;
        this.likedAt = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public User getLiker() {
        return liker;
    }

    public void setLiker(User liker) {
        this.liker = liker;
    }

    public User getLiked() {
        return liked;
    }

    public void setLiked(User liked) {
        this.liked = liked;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }
}
