package com.ethan.adatingapp.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Interest {
    SPORTS("sports"),
    MUSIC("music"),
    TRAVEL("travel"),
    READING("reading"),
    MOVIES("movies"),
    COOKING("cooking"),
    ART("art"),
    TECHNOLOGY("technology"),
    GAMING("gaming"),
    FITNESS("fitness"),
    PHOTOGRAPHY("photography"),
    DANCING("dancing"),
    WRITING("writing"),
    FASHION("fashion"),
    VOLUNTEERING("volunteering"),
    OUTDOORS("outdoors"),
    ANIMALS("animals"),
    FOOD("food"),
    CARS("cars"),
    GARDENING("gardening");

    private final String value;

    Interest(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    private static final Map<String, Interest> VALUE_MAP = new HashMap<>();

    static {
        for (Interest interest : values()) {
            VALUE_MAP.put(interest.value.toLowerCase(), interest);
        }
    }

    @JsonCreator
    public static Interest fromValue(String value) {
        Interest interest = VALUE_MAP.get(value.toLowerCase());
        if (interest == null) {
            throw new IllegalArgumentException("Invalid interest: " + value);
        }
        return interest;
    }
}