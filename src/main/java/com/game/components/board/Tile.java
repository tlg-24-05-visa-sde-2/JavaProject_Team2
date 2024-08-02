package com.game.components.board;

import lombok.Getter;

public class Tile {
    //accessors
    //fields
    @Getter
    private int x_axis;
    @Getter
    private int y_axis;
    private int coordinates;

    //constructor
    public Tile() {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public Tile(int xAxis, int yAxis) {
    }

    public String getCoordinates() {

        return String.format("(%s,%s)",x_axis, y_axis);
    }
    
    public void setCoordinates(int x_axis, int y_axis) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }
}