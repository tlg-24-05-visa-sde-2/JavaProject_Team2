package com.game.components.player;

public class Player {
    String name;
    int ranking;
    boolean win = false;


    public Player(String name){
        this.name = name;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String toString(){
        return "Player: name is: " + name + "\nranking is " + ranking;
    }
}