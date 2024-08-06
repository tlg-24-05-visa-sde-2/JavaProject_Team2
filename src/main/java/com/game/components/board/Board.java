package com.game.components.board;

import java.nio.file.Path;
import java.util.List;

public class Board {
    private Tile[][] tiles;
    private int size;

    public Board(int size, List<TeamPath> teamPaths, List<Coordinate> trackPath) {
        this.size = size;
        tiles = new Tile[size][size];
        initializeBoard(size, teamPaths, trackPath);
    }

    public void initializeBoard(int size, List<TeamPath> teamPaths, List<Coordinate> trackPath) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile(i, j);
                //System.out.println("Created Tile at: " + i + ", " + j);
            }
        }

        if (trackPath != null) {
            for (Coordinate coord : trackPath) {
                if (coord != null) {
                    tiles[coord.getX()][coord.getY()].setPathTile(true);
                    // System.out.println("Set path tile at: " + coord);
                }
            }
        }

        if (teamPaths != null) {
            for (TeamPath teamPath : teamPaths) {
                if (teamPath != null) {
                    Coordinate startingPosition = teamPath.getStartingPosition();
                    Coordinate triggerZone = teamPath.getTriggerZone();
                    Coordinate homeZone = teamPath.getHomeZone();
                    List<Coordinate> safeZone = teamPath.getSafeZone();
                    List<Coordinate> jailZone = teamPath.getJailZone();

                    if (startingPosition != null) {
                        tiles[startingPosition.getX()][startingPosition.getY()].setPathTile(true);
                        //System.out.println("Set starting position for " + teamPath.getTeamColor() + " at: " + startingPosition);
                    }

                    if (triggerZone != null) {
                        tiles[triggerZone.getX()][triggerZone.getY()].setPathTile(true);
                        //System.out.println("Set trigger zone for " + teamPath.getTeamColor() + " at: " + triggerZone);
                    }

                    if (homeZone != null) {
                        tiles[homeZone.getX()][homeZone.getY()].setPathTile(true);
                        //System.out.println("Set home zone for " + teamPath.getTeamColor() + " at: " + homeZone);
                    }

                    if (safeZone != null) {
                        for (Coordinate coord : safeZone) {
                            if (coord != null) {
                                tiles[coord.getX()][coord.getY()].setPathTile(true);
                                //System.out.println("Set safe zone for " + teamPath.getTeamColor() + " at: " + coord);
                            }
                        }
                    }

                    if (jailZone != null) {
                        for (Coordinate coord : jailZone) {
                            if (coord != null) {
                                tiles[coord.getX()][coord.getY()].setPathTile(true);
                                //System.out.println("Set jail zone for " + teamPath.getTeamColor() + " at: " + coord);
                            }
                        }
                    }
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return tiles[x][y];
        }
        return null;
    }
}