package com.ethan.adatingapp.domain.enums;
/*Orientation.java
Orientation Model class
Author:Anita Lottering 222141395
Date:8 May 2025
 */
public enum Orientation {
    STRAIGHT("Straight"),
    GAY("Gay"),
    LESBIAN("Lesbian"),
    BISEXUAL("Bisexual"),
    ASEXUAL("Asexual"),
    PANSEXUAL("Pansexual"),
    QUEER("Queer"),
    QUESTIONING("Questioning"),
    OTHER("Other"),
    PREFER_NOY_TO_SAY("Prefer not to say");

    private final String displayName;

    Orientation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}

