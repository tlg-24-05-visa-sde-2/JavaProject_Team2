package com.game.components;

import java.util.List;

class Board {
    //fields
    private List<SafeZone> safeZones;
    private List<JailZone> jailZones;
    private List<HomeZone> homeZones;
    private List<WhiteSpace> whiteSpaces;

    //constructor
    public Board(List<SafeZone> safeZones, List<JailZone> jailZones, List<HomeZone> homeZones, List<WhiteSpace> whiteSpaces) {
        this.safeZones = safeZones;
        this.jailZones = jailZones;
        this.homeZones = homeZones;
        this.whiteSpaces = whiteSpaces;
    }

    //accessors


    public List<SafeZone> getSafeZones() {
        return safeZones;
    }

    public void setSafeZones(List<SafeZone> safeZones) {
        this.safeZones = safeZones;
    }

    public List<JailZone> getJailZones() {
        return jailZones;
    }

    public void setJailZones(List<JailZone> jailZones) {
        this.jailZones = jailZones;
    }

    public List<HomeZone> getHomeZones() {
        return homeZones;
    }

    public void setHomeZones(List<HomeZone> homeZones) {
        this.homeZones = homeZones;
    }

    public List<WhiteSpace> getWhiteSpaces() {
        return whiteSpaces;
    }

    public void setWhiteSpaces(List<WhiteSpace> whiteSpaces) {
        this.whiteSpaces = whiteSpaces;
    }
}