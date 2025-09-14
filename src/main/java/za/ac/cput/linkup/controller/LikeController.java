package za.ac.cput.linkup.controller;

import za.ac.cput.linkup.domain.Like;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.service.LikeService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.LikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:8081")
public class LikeController {

    private final LikeService likeService;

    private final UserService userService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<LikeDTO> addLike(@RequestParam Long likerId, @RequestParam Long likedId) {
        User liker = userService.read(likerId);
        User liked = userService.read(likedId);

        if (liker == null || liked == null) {
            return ResponseEntity.badRequest().build();
        }

        Like like = likeService.addLike(liker, liked);
        return ResponseEntity.ok(new LikeDTO(like));
    }

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

    @GetMapping("/byLiker/{userId}")
    public ResponseEntity<List<LikeDTO>> getLikesByLiker(@PathVariable Long userId) {
        User liker = userService.read(userId);
        if (liker == null) {
            return ResponseEntity.notFound().build();
        }
        List<Like> likes = likeService.getLikesByLiker(liker);
        List<LikeDTO> dtoLikes = likes.stream().map(like->new LikeDTO(like)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoLikes);
    }

    @GetMapping("/byLiked/{userId}")
    public ResponseEntity<List<LikeDTO>> getLikesByLiked(@PathVariable Long userId) {
        User liked = userService.read(userId);
        if (liked == null) {
            return ResponseEntity.notFound().build();
        }
        List<Like> likes = likeService.getLikesByLiked(liked);
        List<LikeDTO> dtoLikes = likes.stream().map(like->new LikeDTO(like)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoLikes);
    }
}