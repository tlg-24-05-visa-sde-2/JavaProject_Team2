package com.game.components;

import com.game.utilities.TeamColor;

class WhiteSpace {
    //fields
    private int position;
    boolean isOccupied;

    //constructors
    public WhiteSpace(int postion) {
        this.position = postion;
        this.isOccupied = false;
    }

    //accessors
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}