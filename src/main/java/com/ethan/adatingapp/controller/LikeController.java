package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.Like;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.LikeService;
import com.ethan.adatingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    // Endpoint to add a like: user with likerId likes user with likedId
    @PostMapping("/add")
    public ResponseEntity<Like> addLike(@RequestParam Long likerId, @RequestParam Long likedId) {
        User liker = userService.read(likerId);
        User liked = userService.read(likedId);

        if (liker == null || liked == null) {
            return ResponseEntity.badRequest().build();
        }

        Like like = likeService.addLike(liker, liked);
        return ResponseEntity.ok(like);
    }

    // Endpoint to remove a like
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeLike(@RequestParam Long likerId, @RequestParam Long likedId) {
        User liker = userService.read(likerId);
        User liked = userService.read(likedId);

        if (liker == null || liked == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean removed = likeService.removeLike(liker, liked);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Check if liker likes liked
    @GetMapping("/exists")
    public ResponseEntity<Boolean> isLiked(@RequestParam Long likerId, @RequestParam Long likedId) {
        User liker = userService.read(likerId);
        User liked = userService.read(likedId);

        if (liker == null || liked == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean exists = likeService.isLiked(liker, liked);
        return ResponseEntity.ok(exists);
    }

    // Get all likes made by a user
    @GetMapping("/byLiker/{userId}")
    public ResponseEntity<List<Like>> getLikesByLiker(@PathVariable Long userId) {
        User liker = userService.read(userId);
        if (liker == null) {
            return ResponseEntity.notFound().build();
        }
        List<Like> likes = likeService.getLikesByLiker(liker);
        return ResponseEntity.ok(likes);
    }

    // Get all likes received by a user
    @GetMapping("/byLiked/{userId}")
    public ResponseEntity<List<Like>> getLikesByLiked(@PathVariable Long userId) {
        User liked = userService.read(userId);
        if (liked == null) {
            return ResponseEntity.notFound().build();
        }
        List<Like> likes = likeService.getLikesByLiked(liked);
        return ResponseEntity.ok(likes);
    }
}