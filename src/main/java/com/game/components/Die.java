package com.game.components;


import java.util.Arrays;

public class Die {
    int sixCount = 0;
    int[] dieNums = new int[3];
    int currRoll;

    public int rollDice(){
        while(true){
            currRoll = (int)(Math.random()*6)+1;
            if(currRoll == 6){
                dieNums[sixCount] = currRoll;
                sixCount = sixCount + 1;
                if(sixCount == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        return currRoll;
    }

    public void resetDieNums(){
        for(int i = 0; i < dieNums.length; i++){
            dieNums[i] = 0;
        }
    }

    @Override
    public String toString(){
        return "Die: last roll outcome was: " + currRoll + "die rolls: " + Arrays.toString(dieNums);
    }
}
