package com.ethan.adatingapp.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum RelationshipType {
    SHORT_TERM("short_term"),
    LONG_TERM("long_term"),
    FRIENDSHIP("friendship"),
    OPEN_RELATIONSHIP("open_relationship"),
    CASUAL("casual");

    private final String value;

    RelationshipType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    private static final Map<String, RelationshipType> VALUE_MAP = new HashMap<>();

    static {
        for (RelationshipType type : values()) {
            VALUE_MAP.put(type.value.toLowerCase(), type);
        }
    }

    @JsonCreator
    public static RelationshipType fromValue(String value) {
        RelationshipType type = VALUE_MAP.get(value.toLowerCase());
        if (type == null) {
            throw new IllegalArgumentException("Invalid relationship type: " + value);
        }
        return type;
    }
}