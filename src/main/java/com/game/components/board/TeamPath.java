package com.game.components.board;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class TeamPath {
    @JsonbProperty("teamColor")
    private String teamColor;

    @JsonbProperty("startingPosition")
    private Coordinate startingPosition;

    @JsonbProperty("triggerZone")
    private Coordinate triggerZone;

    @JsonbProperty("homeZone")
    private Coordinate homeZone;

    @JsonbProperty("safeZone")
    private List<Coordinate> safeZone;

    @JsonbProperty("jailZone")
    private List<Coordinate> jailZone;

    public TeamPath(String teamColor, Coordinate startingPosition, Coordinate triggerZone, Coordinate homeZone, List<Coordinate> safeZone, List<Coordinate> jailZone, List<Coordinate> trackPath) {
        this.teamColor = teamColor;
        this.startingPosition = startingPosition;
        this.triggerZone = triggerZone;
        this.homeZone = homeZone;
        this.safeZone = safeZone;
        this.jailZone = jailZone;
        this.trackPath = trackPath;
    }

    public List<Coordinate> getTrackPath() {
        return trackPath;
    }

    public void setTrackPath(List<Coordinate> trackPath) {
        this.trackPath = trackPath;
    }

    @JsonbProperty("trackPath")
    private List<Coordinate> trackPath;

    // Default constructor needed for JSON-B
    public TeamPath() {
        System.out.println("TeamPath created with default constructor");
    }

    // Getters and Setters
    public String getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
        System.out.println("TeamColor set to: " + teamColor);
    }

    public Coordinate getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Coordinate startingPosition) {
        this.startingPosition = startingPosition;
        System.out.println("StartingPosition set to: " + startingPosition);
    }

    public Coordinate getTriggerZone() {
        return triggerZone;
    }

    public void setTriggerZone(Coordinate triggerZone) {
        this.triggerZone = triggerZone;
        System.out.println("TriggerZone set to: " + triggerZone);
    }

    public Coordinate getHomeZone() {
        return homeZone;
    }

    public void setHomeZone(Coordinate homeZone) {
        this.homeZone = homeZone;
        System.out.println("HomeZone set to: " + homeZone);
    }

    public List<Coordinate> getSafeZone() {
        return safeZone;
    }

    public void setSafeZone(List<Coordinate> safeZone) {
        this.safeZone = safeZone;
        System.out.println("SafeZone set to: " + safeZone);
    }

    public List<Coordinate> getJailZone() {
        return jailZone;
    }

    public void setJailZone(List<Coordinate> jailZone) {
        this.jailZone = jailZone;
        System.out.println("JailZone set to: " + jailZone);
    }

    @Override
    public String toString() {
        return String.format("TeamPath: [%s: {StartingPosition: %s, TriggerZone: %s, HomeZone: %s, SafeZone: %s, JailZone: %s trackPath: %s]}]",
//                teamColor, startingPosition, triggerZone, homeZone, safeZone, jailZone);
//    }
//}

getClass().getSimpleName(),
                getStartingPosition(),
                getTriggerZone(),
                getSafeZone(),
                getHomeZone(),
                getJailZone(),
        getTrackPath());

    }
}