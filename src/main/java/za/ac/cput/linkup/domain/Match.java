package za.ac.cput.linkup.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "`match`",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user1_id", "user2_id"})
        }
)
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user2;

    private LocalDateTime matchedAt = LocalDateTime.now();

    public Match() {
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public LocalDateTime getMatchedAt() {
        return matchedAt;
    }

    public void setMatchedAt(LocalDateTime matchedAt) {
        this.matchedAt = matchedAt;
    }

    public void setUsers(User a, User b) {
        if (a.getUserId() < b.getUserId()) {
            this.user1 = a;
            this.user2 = b;
        } else {
            this.user1 = b;
            this.user2 = a;
        }
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", matchedAt=" + matchedAt +
                '}';
    }

    public static class Builder{
        private long matchId;
        private User user1;
        private User user2;
        private LocalDateTime matchedAt = LocalDateTime.now();

        public Builder copy(Match match){
            this.matchId = match.matchId;
            this.user1 = match.user1;
            this.user2 = match.user2;
            this.matchedAt = match.matchedAt;
            return this;
        }

        public Builder setMatchId(long matchId) {
            this.matchId = matchId;
            return this;
        }

        public Builder setUser1(User user1) {
            this.user1 = user1;
            return this;
        }

        public Builder setUser2(User user2) {
            this.user2 = user2;
            return this;
        }

        public Builder setMatchedAt(LocalDateTime matchedAt) {
            this.matchedAt = matchedAt;
            return this;
        }

        public Match build(){
            Match match = new Match();
            match.setMatchId(this.matchId);
            match.setUser1(this.user1);
            match.setUser2(this.user2);
            match.setMatchedAt(this.matchedAt);
            return match;
        }
    }

}
