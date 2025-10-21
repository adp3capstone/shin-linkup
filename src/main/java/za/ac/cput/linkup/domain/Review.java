package za.ac.cput.linkup.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private String reviewText;

    private LocalDateTime reviewDate;

    protected Review() {}

    private Review(Builder builder){
        reviewId = builder.reviewId;
        user = builder.user;
        reviewText = builder.reviewText;
        reviewDate = builder.reviewDate;
    }

    public long getReviewId() {
        return reviewId;
    }

    public User getuser() {
        return user;
    }

    public String getReviewText() {
        return reviewText;
    }


    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", user=" + user +
                ", reviewText='" + reviewText + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }

    public static class Builder {
        private long reviewId;
        private User user;
        private String reviewText;
        private LocalDateTime reviewDate;

        public Builder setReviewId(long reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setReviewText(String reviewText) {
            this.reviewText = reviewText;
            return this;
        }

        public Builder setReviewDate(LocalDateTime reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.user = review.user;
            this.reviewText = review.reviewText;
            this.reviewDate = review.reviewDate;
            return this;
        }

        public Review build() {return new Review(this);}
    }
}
