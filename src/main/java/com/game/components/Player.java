package com.game.components;
<<<<<<< HEAD

import com.game.utilities.TeamColor;

import java.util.Arrays;
=======
>>>>>>> 6d5537e29b83bd3a48d0f3e60eb7116f65dd2b17

public class Player {
    private String name;
    public TeamColor playerColor;
    public TeamColor tokenTeamColor;
    int ranking;
    private boolean win = false;
    boolean teamSafeSpace;

    public Player(String name, TeamColor tokenTeamColor){
        this.name = name;
        this.playerColor = tokenTeamColor;
        this.tokenTeamColor = tokenTeamColor;
    }

    public void win(){
        /*
         * IF homeSpace has 2 tokens
         *      {set win to true};
         * display message that PlayerX is the winner??
         */
    }

    public String getName() {
        return name;
    }

    public TeamColor getPlayerColor() {
        return playerColor;
    }

    public TeamColor getTokenTeamColor() {
        return tokenTeamColor;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String toString(){
        return "Player: name is: " + name + "\nplayerColor is: " + playerColor;
    }

}