package com.game.components;

import com.game.utilities.TeamColor;

class HomeZone {
    //fields
    private TeamColor teamColor;
    private int tokenCount;

    //constructors
    public HomeZone(TeamColor teamColor, int tokenCount) {
        this.teamColor = teamColor;
        this.tokenCount = tokenCount;
    }

    //accessors
    public TeamColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public int getTokenCount() {
        return tokenCount;
    }

    public void setTokenCount(int tokenCount) {
        this.tokenCount = tokenCount;
    }


}