package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.Like;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.repository.LikeRepository;
import com.ethan.adatingapp.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, MatchRepository matchRepository) {
        this.likeRepository = likeRepository;
        this.matchRepository = matchRepository;
    }

    // Create a like (userA likes userB)
    public Like addLike(User liker, User liked) {
        // Prevent duplicate likes
        Like existingLike = likeRepository.findByLikerUserIdAndLikedUserId(
                liker.getUserId(), liked.getUserId());
        if (existingLike != null) {
            return existingLike;
        }

        // Save the like first
        Like like = new Like(liker, liked);
        likeRepository.save(like);

        // Check if reciprocal like exists
        if (likeRepository.existsByLikerAndLiked(liked, liker)) {
            User user1 = liker;
            User user2 = liked;

            // Enforce order by ID to avoid duplicates
            if (user1.getUserId() > user2.getUserId()) {
                User tmp = user1;
                user1 = user2;
                user2 = tmp;
            }
        }

        return like;
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
