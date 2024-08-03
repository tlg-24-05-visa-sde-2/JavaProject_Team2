package com.game.components.board;

import com.game.utilities.TeamColor;


public class SafeZone {
    private boolean safeSpace;
    private TeamColor teamColor;
    private boolean triggerSpace;

    public SafeZone(TeamColor teamColor) {
        this.teamColor = teamColor;
        this.safeSpace = true;
        this.triggerSpace = false;
    }

    // Getters and setters
    public boolean isSafeSpace() {
        return safeSpace;
    }

    public void setSafeSpace(boolean safeSpace) {
        this.safeSpace = safeSpace;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public boolean isTriggerSpace() {
        return triggerSpace;
    }

    public void setTriggerSpace(boolean triggerSpace) {
        this.triggerSpace = triggerSpace;
    }
}
