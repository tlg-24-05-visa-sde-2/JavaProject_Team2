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
        if (name == null || name.trim() == "") {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        if (tokenTeamColor == null || !isValidTeamColor(tokenTeamColor)) {
            throw new IllegalArgumentException("Invalid team color: " + tokenTeamColor + "\n valid options are " + TeamColor.values());
        }
        this.name = name;
        this.playerColor = tokenTeamColor;
        this.tokenTeamColor = tokenTeamColor;
        this.token = new Token(this, tokenTeamColor);
    }

    public void win(){
        win = true;
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

    private boolean isValidTeamColor(TeamColor teamColor) {
        for (TeamColor color : TeamColor.values()) {
            if (color == teamColor) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return "Player: name is: " + name + "\nplayerColor is: " + playerColor;
    }
}
