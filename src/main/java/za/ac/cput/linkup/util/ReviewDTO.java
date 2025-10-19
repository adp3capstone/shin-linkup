package za.ac.cput.linkup.util;

public class ReviewDTO {
    private String reviewText;
    private String username;

    public ReviewDTO(){}

    public ReviewDTO(String reviewText, String username) {
        this.reviewText = reviewText;
        this.username = username;
    }


    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewText='" + reviewText + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}