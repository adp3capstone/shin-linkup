package za.ac.cput.linkup.service;

import org.springframework.stereotype.Service;
import za.ac.cput.linkup.domain.Review;
import za.ac.cput.linkup.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }


    @Override
    public Review create(Review review) {
        return this.repository.save(review);
    }

    @Override
    public Review read(Long reviewId) {
        return this.repository.findById(reviewId).orElse(null);
    }

    @Override
    public Review update(Review review) {
        return this.repository.save(review);
    }

    @Override
    public List<Review> getAll() {
        return this.repository.findAll();
    }
}
