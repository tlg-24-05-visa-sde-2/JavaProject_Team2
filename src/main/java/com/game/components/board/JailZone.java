package com.game.components.board;

import com.game.utilities.TeamColor;

class JailZone {
    //fields
    private TeamColor teamColor;
    private int jailSize;
    private int prisonCount;
    private boolean hasTokens;

    //constructor
    public JailZone(TeamColor teamColor, int jailSize) {
        this.teamColor = teamColor;
        this.jailSize = jailSize;
    }

    //accessors
    public TeamColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public int getJailSize() {
        return jailSize;
    }

    public void setJailSize(int jailSize) {
        this.jailSize = jailSize;
    }

    public int getPrisonCount() {
        return prisonCount;
    }

    public void setPrisonCount(int prisonCount) {
        this.prisonCount = prisonCount;
    }

    public boolean isHasTokens() {
        return hasTokens;
    }

    public void setHasTokens(boolean hasTokens) {
        this.hasTokens = hasTokens;
    }
}