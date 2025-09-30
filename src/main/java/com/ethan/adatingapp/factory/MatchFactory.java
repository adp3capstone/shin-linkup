package com.ethan.adatingapp.factory;

import com.ethan.adatingapp.domain.Match;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.util.Helper;

import java.time.LocalDateTime;

public class MatchFactory {
    public static Match buildMatch( User user1, User user2, LocalDateTime matchedAt) {
        if (Helper.isObjectNull(user1) || Helper.isObjectNull(user2)) {
            throw new IllegalArgumentException("Users cannot be null");
        }

        if (user1.getUserId() == user2.getUserId()) {
            throw new IllegalArgumentException("A user cannot match with themselves");
        }


        return new Match.Builder()
                .setUser1(user1)
                .setUser2(user2)
                .setMatchedAt(matchedAt == null ? LocalDateTime.now() : matchedAt)
                .build();
    }
}
