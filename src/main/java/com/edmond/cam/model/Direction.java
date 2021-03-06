package com.edmond.cam.model;

public enum Direction {

    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT,
    ;

    public static Direction buildDirection(String name) {
        if (name.equalsIgnoreCase(FORWARD.name())) {
            return FORWARD;
        } else if (name.equalsIgnoreCase(BACKWARD.name())) {
            return BACKWARD;
        } else if (name.equalsIgnoreCase(LEFT.name())) {
            return LEFT;
        } else if (name.equalsIgnoreCase(RIGHT.name())) {
            return RIGHT;
        } else {
            throw new IllegalArgumentException("Unknown direction name");
        }
    }
}
