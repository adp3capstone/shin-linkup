package za.ac.cput.linkup.factory;

import za.ac.cput.linkup.domain.Review;
import za.ac.cput.linkup.util.Helper;

import java.time.LocalDateTime;

public class ReviewFactory {
    public static Review create(String reviewText, LocalDateTime reviewDate) {
        if(Helper.isStringNullOrEmpty(reviewText) || Helper.isDateNull(reviewDate)){
            return null;}

        return new Review.Builder()
                .setReviewText(reviewText)
                .setReviewDate(reviewDate)
                .build();
    }
}
