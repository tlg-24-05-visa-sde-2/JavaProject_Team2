package com.game.controller;

import com.game.components.board.Coordinate;
import com.game.components.board.Path;
import com.game.components.board.TeamPath;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.json.bind.annotation.JsonbProperty;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PathController {
    private List<Coordinate> boardPath;
    private List<TeamPath> teamPaths;

    public PathController(String resourcePath) {
        try {
            loadPathsFromJson("/boardpath.json");
        } catch (Exception e) {
            System.err.println("Failed to load JSON file, using hardcoded paths.");
            boardPath = convertToCoordinates(Path.ax[0], Path.ay[0]);
            teamPaths = createTeamPaths();
        }
    }

    private void loadPathsFromJson(String resourcePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream != null) {
                Jsonb jsonb = JsonbBuilder.create();
                PathData pathData = jsonb.fromJson(inputStream, PathData.class);
                this.boardPath = pathData.boardPath;
                this.teamPaths = pathData.teamPaths;
            } else {
                System.err.println("JSON file not found!");
                boardPath = convertToCoordinates(Path.ax[0], Path.ay[0]);
                teamPaths = createTeamPaths();
            }
        } catch (IOException | JsonbException e) {
            e.printStackTrace();
            boardPath = convertToCoordinates(Path.ax[0], Path.ay[0]);
            teamPaths = createTeamPaths();
        }
    }

    public List<Coordinate> getBoardPath() {
        return boardPath;
    }

    public List<TeamPath> getTeamPaths() {
        return teamPaths;
    }

    private List<Coordinate> convertToCoordinates(int[] x, int[] y) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            coordinates.add(new Coordinate(x[i], y[i]));
        }
        return coordinates;
    }

    private List<TeamPath> createTeamPaths() {
        List<TeamPath> teamPaths = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            List<Coordinate> safeZone = new ArrayList<>();
            List<Coordinate> jailZone = new ArrayList<>();
            List<Coordinate> trackPath = convertToCoordinates(Path.ax[i], Path.ay[i]);

            // Adjust according to your game's logic for safeZone and jailZone coordinates
            for (int j = 0; j < 4; j++) {
                safeZone.add(new Coordinate(Path.initialx[i][j], Path.initialy[i][j]));
            }
            // Example to fill the jailZone
            jailZone.add(new Coordinate(Path.initialx[i][0], Path.initialy[i][0]));

            teamPaths.add(new TeamPath(
                    getTeamColor(i),
                    new Coordinate(Path.initialx[i][0], Path.initialy[i][0]),
                    new Coordinate(Path.ax[i][0], Path.ay[i][0]),
                    new Coordinate(Path.ax[i][Path.ax[i].length - 1], Path.ay[i][Path.ay[i].length - 1]),
                    safeZone,
                    jailZone,
                    trackPath
            ));
        }

        return teamPaths;
    }

    private String getTeamColor(int index) {
        switch (index) {
            case 0:
                return "RED";
            case 1:
                return "GREEN";
            case 2:
                return "YELLOW";
            case 3:
                return "BLUE";
            default:
                return "UNKNOWN";
        }
    }

    public static class PathData {
        @JsonbProperty("boardPath")
        public List<Coordinate> boardPath;

        @JsonbProperty("teamPaths")
        public List<TeamPath> teamPaths;
    }
}


