package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.Like;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Create a like (userA likes userB)
    public Like addLike(User liker, User liked) {
        Like existingLike = likeRepository.findByLikerUserIdAndLikedUserId(liker.getUserId(), liked.getUserId());
        if (existingLike != null) {
            return existingLike; // or throw exception or handle as needed
        }

        Like like = new Like(liker, liked);
        return likeRepository.save(like);
    }

    // Remove a like
    public boolean removeLike(User liker, User liked) {
        Like existingLike = likeRepository.findByLikerUserIdAndLikedUserId(liker.getUserId(), liked.getUserId());
        if (existingLike != null) {
            likeRepository.delete(existingLike);
            return true;
        }
        return false;
    }

    // Check if user A likes user B
    public boolean isLiked(User liker, User liked) {
        return likeRepository.existsByLikerUserIdAndLikedUserId(liker.getUserId(), liked.getUserId());
    }

    // Get all likes given by a specific user
    public List<Like> getLikesByLiker(User liker) {
        return likeRepository.findAllByLiker(liker);
    }

    // Get all likes received by a specific user
    public List<Like> getLikesByLiked(User liked) {
        return likeRepository.findAllByLiked(liked);
    }
}
