package com.game.components.player;

import com.game.components.Token;
import com.game.utilities.TeamColor;

public class Player {
    private String name;
    public TeamColor playerColor;
    public TeamColor tokenTeamColor;
    public Token token;
    private boolean win = false;

    public Player(String name, TeamColor tokenTeamColor){
        this.name = name;
        this.playerColor = tokenTeamColor;
        this.tokenTeamColor = tokenTeamColor;
        this.token = new Token(this, tokenTeamColor);
    }

    public void win(){
        /*
         * call the gui method to display winner here
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

    public String toString(){
        return "Player: name is: " + name + "\nplayerColor is: " + playerColor;
    }
}