package za.ac.cput.linkup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.linkup.domain.Review;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.service.IReviewService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.ReviewDTO;
import za.ac.cput.linkup.util.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final IReviewService reviewService;
    private final UserService userService;

    @Autowired
    public ReviewController(IReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    // -------------------- CREATE --------------------
    @PostMapping("/create")
    public ResponseEntity<Review> create(@RequestBody ReviewDTO reviewRequest) {

        UserDTO userDTO = reviewRequest.getUser(); // ✅ Now expecting a full UserDTO
        if (userDTO == null || userDTO.getUsername() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 1. Find the actual User entity by username
        Optional<User> userOpt = userService.findByUsername(userDTO.getUsername());
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOpt.get();

        // 2. Build Review entity
        Review newReview = new Review.Builder()
                .setUser(user)
                .setReviewText(reviewRequest.getReviewText())
                .setReviewDate(LocalDateTime.now())
                .build();

        // 3. Save and return
        Review savedReview = reviewService.create(newReview);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // -------------------- READ --------------------
    @GetMapping("/read/{reviewId}")
    public ResponseEntity<Review> read(@PathVariable long reviewId) {
        Review review = reviewService.read(reviewId);
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // -------------------- READ ALL --------------------
    @GetMapping("/getAll")
    public ResponseEntity<List<Review>> getAll() {
        List<Review> reviews = reviewService.getAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // -------------------- UPDATE --------------------
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<Review> update(
            @PathVariable long reviewId,
            @RequestBody ReviewDTO reviewRequest) {

        Review existingReview = reviewService.read(reviewId);
        if (existingReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = reviewRequest.getUser();
        if (userDTO == null || userDTO.getUsername() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Check if the user making the request is the review author
        if (!existingReview.getUser().getUsername().equals(userDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Rebuild updated review using builder copy
        Review updatedReview = new Review.Builder()
                .copy(existingReview)
                .setReviewText(reviewRequest.getReviewText())
                .setReviewDate(LocalDateTime.now())
                .build();

        Review savedReview = reviewService.update(updatedReview);
        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    // -------------------- DELETE --------------------
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> delete(
            @PathVariable long reviewId,
            @RequestParam String username) {

        Review existingReview = reviewService.read(reviewId);
        if (existingReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!existingReview.getUser().getUsername().equals(username)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reviewService.delete(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
