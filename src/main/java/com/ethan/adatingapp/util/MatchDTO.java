package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.Match;

import java.time.LocalDateTime;

public class MatchDTO {

    private long matchId;
    private long user1Id;
    private long user2Id;
    private LocalDateTime matchedAt;

    // Constructor that takes a Match object
    public MatchDTO(Match match) {
        this.matchId = match.getMatchId();
        this.user1Id = match.getUser1().getUserId();
        this.user2Id = match.getUser2().getUserId();
        this.matchedAt = match.getMatchedAt();
    }

    // Getters and setters
    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(long user1Id) {
        this.user1Id = user1Id;
    }

    public long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(long user2Id) {
        this.user2Id = user2Id;
    }

    public LocalDateTime getMatchedAt() {
        return matchedAt;
    }

    public void setMatchedAt(LocalDateTime matchedAt) {
        this.matchedAt = matchedAt;
    }
}
