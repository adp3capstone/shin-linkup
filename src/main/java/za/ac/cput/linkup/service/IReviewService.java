package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.Review;

import java.util.List;

public interface IReviewService extends IService<Review, Long> {
    List<Review>getAll();

    boolean delete(long reviewId);
}
