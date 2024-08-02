package com.game.components.player;

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