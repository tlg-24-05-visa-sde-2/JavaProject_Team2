package com.game.components;

public class Die {
    int diceNumber;

    public int rollDice(){
        return diceNumber = (int)(Math.random()*6)+1;
    }

    @Override
    public String toString(){
        return "Die: last roll outcome was: " + diceNumber;
    }
}