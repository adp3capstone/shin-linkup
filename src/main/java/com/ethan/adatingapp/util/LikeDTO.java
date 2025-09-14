package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.Like;

import java.time.LocalDateTime;

public class LikeDTO {

    private Long id;
    private Long likerId;
    private Long likedId;
    private LocalDateTime likedAt;

    public LikeDTO(Like like) {
        this.id = like.getId();
        this.likerId = like.getLiker().getUserId();
        this.likedId = like.getLiked().getUserId();
        this.likedAt = like.getLikedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikerId() {
        return likerId;
    }

    public void setLikerId(Long likerId) {
        this.likerId = likerId;
    }

    public Long getLikedId() {
        return likedId;
    }

    public void setLikedId(Long likedId) {
        this.likedId = likedId;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }
}
