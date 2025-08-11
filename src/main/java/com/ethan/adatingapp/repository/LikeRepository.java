package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.Like;
import com.ethan.adatingapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByLikerIdAndLikedId(Long likerId, Long likedId);

    Optional<Like> findByLikerIdAndLikedId(Long likerId, Long likedId);

    long countByLikedId(Long likedId);

    List<Like> findAllByLiker(User liker);

    List<Like> findAllByLiked(User liked);
}
