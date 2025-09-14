package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.Like;
import com.ethan.adatingapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByLikerUserIdAndLikedUserId(Long likerId, Long likedId);

    // In LikeRepository.java
    Like findByLikerUserIdAndLikedUserId(Long likerId, Long likedId);

    List<Like> findAllByLiker(User liker);

    List<Like> findAllByLiked(User liked);

    boolean existsByLikerAndLiked(User liker, User liked);
}