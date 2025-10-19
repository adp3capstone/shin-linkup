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

import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("/create")
    public ResponseEntity<Review> create(@RequestBody ReviewDTO reviewRequest) {

        // 1. Find the User object (using the username passed in the DTO)
        String username = reviewRequest.getUsername();
        if (username == null || username.isEmpty()) {
            // Handle case where username is missing (bad request)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username)); // Error if user doesn't exist

        // 2. Build the Review object (Business Logic in Controller)
        Review newReview = new Review.Builder()
                .setUser(user)
                .setReviewText(reviewRequest.getReviewText())
                .setReviewDate(LocalDateTime.now())
                .build();

        // 3. Save and return (Calling the simple Service method)
        Review savedReview = reviewService.create(newReview);

        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping("/read/{reviewId}")
    public Review read(@PathVariable long reviewId) {
        return reviewService.read(reviewId);
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return reviewService.getAll();
    }

//    private final IReviewService reviewService;
//
//    @Autowired
//    public ReviewController(IReviewService reviewService) {
//        this.reviewService = reviewService;
//    }
//
//    @PostMapping("/create")
//    public Review create(@RequestBody Review review) {
//        return reviewService.create(review);
//    }
//
//    @GetMapping("/read/{reviewId}")
//    public Review read(@PathVariable long reviewId) {
//        return reviewService.read(reviewId);
//    }
//
//    @GetMapping("/getAll")
//    public List<Review> getAll() {
//        return reviewService.getAll();
//    }


}
