package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.Match;

import java.time.LocalDateTime;
import java.util.Base64;

public class MatchWithUserDetailsDTO {

    private long matchId;
    private long user1Id;
    private String user1FirstName;
    private String user1LastName;
    private long user2Id;
    private String user2FirstName;
    private String user2LastName;
    private LocalDateTime matchedAt;
    private String imageBase64;

    public MatchWithUserDetailsDTO(Match match) {
        this.matchId = match.getMatchId();

        if (match.getUser1() != null) {
            this.user1Id = match.getUser1().getUserId();
            this.user1FirstName = match.getUser1().getFirstName();
            this.user1LastName = match.getUser1().getLastName();
        }

        if (match.getUser2() != null) {
            this.user2Id = match.getUser2().getUserId();
            this.user2FirstName = match.getUser2().getFirstName();
            this.user2LastName = match.getUser2().getLastName();

            if (match.getUser2().getImage() != null && match.getUser2().getImage().getImageUrl() != null) {
                try {
                    this.imageBase64 = Base64.getEncoder().encodeToString(match.getUser2().getImage().getImageUrl());
                } catch (Exception e) {
                    this.imageBase64 = null;
                }
            }
        }

        this.matchedAt = match.getMatchedAt();
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

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

    public String getUser1FirstName() {
        return user1FirstName;
    }

    public void setUser1FirstName(String user1FirstName) {
        this.user1FirstName = user1FirstName;
    }

    public String getUser1LastName() {
        return user1LastName;
    }

    public void setUser1LastName(String user1LastName) {
        this.user1LastName = user1LastName;
    }

    public String getUser2FirstName() {
        return user2FirstName;
    }

    public void setUser2FirstName(String user2FirstName) {
        this.user2FirstName = user2FirstName;
    }

    public String getUser2LastName() {
        return user2LastName;
    }

    public void setUser2LastName(String user2LastName) {
        this.user2LastName = user2LastName;
    }
}
