package com.game.components.board;

import com.game.components.Token;
import com.game.utilities.TeamColor;

import java.util.List;


public class Path extends Tile {
    //fields
    private boolean isOccupied;
    private boolean hasBeenBlocked;
    private TeamColor teamColor;
    private List<Integer> coordinates; //TODO: path reads from json file coordinates.
    private Token token;
    private int tokenCount;
    public static int MAX_TOKENS_ON_TILE = 2;

    //constructors

public Path(int x_axis, int y_axis) {
    super(x_axis, y_axis);
}

    //method
    public void blockEnemyToken(Token token1, Token token2){

    //check if tile isOccupied
        if(isOccupied){
           tokenCount++;

            //compare two token
            if(token1.tokenTeamColor.equals(token2.tokenTeamColor)){
                token2.setCurrentTokenLocation(token.getCurrentTokenLocationOnTile());
                this.tokenCount= MAX_TOKENS_ON_TILE;
                hasBeenBlocked= true;
        }
            //TODO: add method to playerController during turn execution sequence for block condition.
    };
    //isOccupied = true;

    }
}