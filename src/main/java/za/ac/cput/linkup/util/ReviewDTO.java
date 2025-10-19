package za.ac.cput.linkup.util;

public class ReviewDTO {

    private Long reviewId;
    private String reviewText;
    private UserDTO user; // ✅ includes the user info for the review creator

    public ReviewDTO() {}

    public Long getReviewId() { return reviewId; }
    public void setReviewId(Long reviewId) { this.reviewId = reviewId; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

}