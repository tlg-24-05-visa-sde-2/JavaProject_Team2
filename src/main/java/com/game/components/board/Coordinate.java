package com.game.components.board;

import jakarta.json.bind.annotation.JsonbProperty;

public class Coordinate {
    @JsonbProperty("x")
    private int x;

    @JsonbProperty("y")
    private int y;

    // Default constructor needed for JSON-B
    public Coordinate() {
        //System.out.println("Coordinate created with default constructor");
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
       // System.out.println("Coordinate created with values: " + toString());
    }

    // Accessors
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // toString
    @Override
    public String toString() {
        return String.format("Coordinate: (%d, %d)", getX(), getY());
    }
}

