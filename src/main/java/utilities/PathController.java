
package utilities;

import tile.Coordinate;
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

//package utilities;
//import jakarta.json.bind.Jsonb;
//import jakarta.json.bind.JsonbBuilder;
//import jakarta.json.bind.JsonbException;
//import jakarta.json.bind.annotation.JsonbProperty;
//import tile.Coordinate;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//
//public class PathController {
//    private List<Coordinate> boardPath;
//    private List<TeamPath> teamPaths;
//
//    public PathController(String resourcePath) {
//        try {
//            loadPathsFromJson(resourcePath);
//        } catch (Exception e) {
//            System.err.println("Failed to load JSON file, using hardcoded paths.");
//            boardPath = HARDCODED_BOARD_PATH;
//            teamPaths = HARDCODED_TEAM_PATHS;
//        }
//    }
//
//private void loadPathsFromJson(String resourcePath) {
//    try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
//        if (inputStream != null) {
//            Jsonb jsonb = JsonbBuilder.create();
//            PathData pathData = jsonb.fromJson(inputStream, PathData.class);
//            this.boardPath = pathData.boardPath;
//            this.teamPaths = pathData.teamPaths;
//        } else {
//            System.err.println("JSON file not found!");
//            boardPath = HARDCODED_BOARD_PATH;
//            teamPaths = HARDCODED_TEAM_PATHS;
//        }
//    } catch (IOException | JsonbException e) {
//        e.printStackTrace();
//        boardPath = HARDCODED_BOARD_PATH;
//        teamPaths = HARDCODED_TEAM_PATHS;
//    }
//}
//
//    public List<Coordinate> getBoardPath() {
//        return boardPath;
//    }
//
//    public List<TeamPath> getTeamPaths() {
//        return teamPaths;
//    }
//
//    private static final List<Coordinate> HARDCODED_BOARD_PATH = List.of(
//            new Coordinate(14, 8), new Coordinate(12, 8), new Coordinate(11, 8),
//            new Coordinate(10, 8), new Coordinate(9, 8), new Coordinate(8, 9),
//            new Coordinate(8, 10), new Coordinate(8, 11), new Coordinate(8, 12),
//            new Coordinate(8, 13), new Coordinate(8, 14), new Coordinate(7, 14),
//            new Coordinate(6, 14), new Coordinate(6, 12), new Coordinate(6, 11),
//            new Coordinate(6, 10), new Coordinate(6, 9), new Coordinate(5, 8),
//            new Coordinate(4, 8), new Coordinate(3, 8), new Coordinate(2, 8),
//            new Coordinate(1, 8), new Coordinate(0, 8), new Coordinate(0, 7),
//            new Coordinate(0, 6), new Coordinate(2, 6), new Coordinate(3, 6),
//            new Coordinate(4, 6), new Coordinate(5, 6), new Coordinate(6, 5),
//            new Coordinate(6, 4), new Coordinate(6, 3), new Coordinate(6, 2),
//            new Coordinate(6, 1), new Coordinate(6, 0), new Coordinate(7, 0),
//            new Coordinate(8, 0), new Coordinate(8, 2), new Coordinate(8, 3),
//            new Coordinate(8, 4), new Coordinate(8, 5), new Coordinate(9, 6),
//            new Coordinate(10, 6), new Coordinate(11, 6), new Coordinate(12, 6),
//            new Coordinate(13, 6), new Coordinate(14, 6), new Coordinate(14, 7)
//    );
//
//    private static final List<TeamPath> HARDCODED_TEAM_PATHS = List.of(
//            new TeamPath("BLUE",
//                    new Coordinate(6, 13), new Coordinate(7, 14), new Coordinate(7, 8),
//                    List.of(new Coordinate(7, 13), new Coordinate(7, 12), new Coordinate(7, 11), new Coordinate(7, 10), new Coordinate(7, 9)),
//                    List.of(new Coordinate(1, 11), new Coordinate(4, 11), new Coordinate(2, 12), new Coordinate(3, 12)),
//                    List.of(new Coordinate(6, 12), new Coordinate(6, 11), new Coordinate(6, 10), new Coordinate(6, 9), new Coordinate(5, 8),
//                            new Coordinate(4, 8), new Coordinate(3, 8), new Coordinate(2, 8), new Coordinate(1, 8), new Coordinate(0, 8),
//                            new Coordinate(0, 7), new Coordinate(0, 6), new Coordinate(2, 6), new Coordinate(3, 6), new Coordinate(4, 6),
//                            new Coordinate(5, 6), new Coordinate(6, 5), new Coordinate(6, 4), new Coordinate(6, 3), new Coordinate(6, 2),
//                            new Coordinate(6, 1), new Coordinate(6, 0), new Coordinate(7, 0), new Coordinate(8, 0), new Coordinate(8, 2),
//                            new Coordinate(8, 3), new Coordinate(8, 4), new Coordinate(8, 5), new Coordinate(9, 6), new Coordinate(10, 6),
//                            new Coordinate(11, 6), new Coordinate(12, 6), new Coordinate(13, 6), new Coordinate(14, 6), new Coordinate(14, 7),
//                            new Coordinate(14, 8), new Coordinate(12, 8), new Coordinate(11, 8), new Coordinate(10, 8), new Coordinate(9, 8),
//                            new Coordinate(8, 9), new Coordinate(8, 10), new Coordinate(8, 11), new Coordinate(8, 12), new Coordinate(8, 13),
//                            new Coordinate(8, 14), new Coordinate(7, 14), new Coordinate(6, 14))
//            ),
//            new TeamPath("GREEN",
//                    new Coordinate(8, 1), new Coordinate(7, 0), new Coordinate(7, 6),
//                    List.of(new Coordinate(7, 1), new Coordinate(7, 2), new Coordinate(7, 3), new Coordinate(7, 4), new Coordinate(7, 5)),
//                    List.of(new Coordinate(12, 1), new Coordinate(11, 3), new Coordinate(13, 3), new Coordinate(12, 2)),
//                    List.of(new Coordinate(8, 2), new Coordinate(8, 3), new Coordinate(8, 4), new Coordinate(8, 5), new Coordinate(9, 6),
//                            new Coordinate(10, 6), new Coordinate(11, 6), new Coordinate(12, 6), new Coordinate(13, 6), new Coordinate(14, 6),
//                            new Coordinate(14, 7), new Coordinate(12, 8), new Coordinate(11, 8), new Coordinate(10, 8), new Coordinate(9, 8),
//                            new Coordinate(8, 9), new Coordinate(8, 10), new Coordinate(8, 11), new Coordinate(8, 12), new Coordinate(8, 13),
//                            new Coordinate(8, 14), new Coordinate(7, 14), new Coordinate(6, 14), new Coordinate(6, 12), new Coordinate(6, 11),
//                            new Coordinate(6, 10), new Coordinate(6, 9), new Coordinate(5, 8), new Coordinate(4, 8), new Coordinate(3, 8),
//                            new Coordinate(2, 8), new Coordinate(1, 8), new Coordinate(0, 8), new Coordinate(0, 7), new Coordinate(0, 6),
//                            new Coordinate(2, 6), new Coordinate(3, 6), new Coordinate(4, 6), new Coordinate(5, 6), new Coordinate(6, 5),
//                            new Coordinate(6, 4), new Coordinate(6, 3), new Coordinate(6, 2), new Coordinate(6, 1), new Coordinate(6, 0),
//                            new Coordinate(7, 0))
//            ),
//            new TeamPath("RED",
//                    new Coordinate(1, 6), new Coordinate(0, 7), new Coordinate(6, 7),
//                    List.of(new Coordinate(1, 7), new Coordinate(2, 7), new Coordinate(3, 7), new Coordinate(4, 7), new Coordinate(5, 7)),
//                    List.of(new Coordinate(3, 3), new Coordinate(1, 3), new Coordinate(1, 1), new Coordinate(3, 2)),
//                    List.of(new Coordinate(2, 6), new Coordinate(3, 6), new Coordinate(4, 6), new Coordinate(5, 6), new Coordinate(6, 5),
//                            new Coordinate(6, 4), new Coordinate(6, 3), new Coordinate(6, 2), new Coordinate(6, 1), new Coordinate(6, 0),
//                            new Coordinate(7, 0), new Coordinate(8, 0), new Coordinate(8, 2), new Coordinate(8, 3), new Coordinate(8, 4),
//                            new Coordinate(8, 5), new Coordinate(9, 6), new Coordinate(10, 6), new Coordinate(11, 6), new Coordinate(12, 6),
//                            new Coordinate(13, 6), new Coordinate(14, 6), new Coordinate(14, 7), new Coordinate(14, 8), new Coordinate(12, 8),
//                            new Coordinate(11, 8), new Coordinate(10, 8), new Coordinate(9, 8), new Coordinate(8, 9), new Coordinate(8, 10),
//                            new Coordinate(8, 11), new Coordinate(8, 12), new Coordinate(8, 13), new Coordinate(8, 14), new Coordinate(7, 14),
//                            new Coordinate(6, 14), new Coordinate(6, 12), new Coordinate(6, 11), new Coordinate(6, 10), new Coordinate(6, 9),
//                            new Coordinate(5, 8), new Coordinate(4, 8), new Coordinate(3, 8), new Coordinate(2, 8), new Coordinate(1, 8),
//                            new Coordinate(0, 8), new Coordinate(0, 7))
//            ),
//            new TeamPath("YELLOW",
//                    new Coordinate(13, 8), new Coordinate(14, 7), new Coordinate(8, 7),
//                    List.of(new Coordinate(13, 7), new Coordinate(12, 7), new Coordinate(11, 7), new Coordinate(10, 7), new Coordinate(9, 7)),
//                    List.of(new Coordinate(11, 10), new Coordinate(13, 11), new Coordinate(12, 12), new Coordinate(13, 12)),
//                    List.of(new Coordinate(12, 8), new Coordinate(11, 8), new Coordinate(10, 8), new Coordinate(9, 8), new Coordinate(8, 9),
//                            new Coordinate(8, 10), new Coordinate(8, 11), new Coordinate(8, 12), new Coordinate(8, 13), new Coordinate(8, 14),
//                            new Coordinate(7, 14), new Coordinate(6, 14), new Coordinate(6, 12), new Coordinate(6, 11), new Coordinate(6, 10),
//                            new Coordinate(6, 9), new Coordinate(5, 8), new Coordinate(4, 8), new Coordinate(3, 8), new Coordinate(2, 8),
//                            new Coordinate(1, 8), new Coordinate(0, 8), new Coordinate(0, 7), new Coordinate(0, 6), new Coordinate(2, 6),
//                            new Coordinate(3, 6), new Coordinate(4, 6), new Coordinate(5, 6), new Coordinate(6, 5), new Coordinate(6, 4),
//                            new Coordinate(6, 3), new Coordinate(6, 2), new Coordinate(6, 1), new Coordinate(6, 0), new Coordinate(7, 0),
//                            new Coordinate(8, 0), new Coordinate(8, 2), new Coordinate(8, 3), new Coordinate(8, 4), new Coordinate(8, 5),
//                            new Coordinate(9, 6), new Coordinate(10, 6), new Coordinate(11, 6), new Coordinate(12, 6), new Coordinate(13, 6),
//                            new Coordinate(14, 6), new Coordinate(14, 7))
//            )
//    );
//
//    public static class PathData {
//        @JsonbProperty("boardPath")
//        public List<Coordinate> boardPath;
//
//        @JsonbProperty("teamPaths")
//        public List<TeamPath> teamPaths;
//    }
//}
//
//
////
////public class PathController {
////    private Map<String, TeamPath> teamPaths;
////    private List<Coordinate> trackPath;
////
////    public PathController(String resourcePath) {
////        loadPathsFromJson(resourcePath);
////    }
////
////    private void loadPathsFromJson(String resourcePath) {
////        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
////            if (inputStream != null) {
////                Jsonb jsonb = JsonbBuilder.create();
////                PathData pathData = jsonb.fromJson(inputStream, PathData.class);
////                this.teamPaths = pathData.teamPaths;
////                this.trackPath = pathData.trackPath;
////            } else {
////                System.err.println("JSON file not found!");
////            }
////        } catch (IOException | JsonbException e) {
////            e.printStackTrace();
////        }
////    }
////
////    public Map<String, TeamPath> getTeamPaths() {
////        return teamPaths;
////    }
////
////    public List<Coordinate> getTrackPath() {
////        return trackPath;
////    }
////
////    // Inner class to represent the structure of the JSON file
////    public static class PathData {
////        public Map<String, TeamPath> teamPaths;
////        public List<Coordinate> trackPath;
////    }
////}
////
////
//
