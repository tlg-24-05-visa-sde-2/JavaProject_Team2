//package com.game.components;
//
//import com.game.components.board.Tile;
//import com.game.components.player.Player;
//import com.game.utilities.TeamColor;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class Token {
//    boolean teamSafeSpace = false;
//    public TeamColor tokenTeamColor;
//    public Map<String, Map<String, Integer>> positions; // store current token location for player
//    private Map<TeamColor, List<Map<String, Integer>>> safeZone;
//
//    // constructor that takes requires Team color
//    public Token(TeamColor tokenTeamColor) {
//        this.tokenTeamColor = tokenTeamColor;
//        this.positions = new HashMap<>();
//        this.safeZone = new HashMap<>();
//
//        // Initialize two tokens at 0,0
//        addToken("token1", 0, 0);
//        addToken("token2", 0, 0);
//
//        // Initialize safe zone for player
//        initializeSafeZone(tokenTeamColor);
//    }
//
//    // Method to initialize safe ZONES based on the user's team color
//    private void initializeSafeZone(TeamColor teamColor) {
//        List<Map<String, Integer>> safeZoneCoordinates = new ArrayList<>();
//        switch (teamColor) {
//            case RED:
//                addSafeZone(safeZoneCoordinates, 5, 10);  // replace the int with the actual safezone square
//                addSafeZone(safeZoneCoordinates, 230, 210);  // *** add more safezone maps for homezone path ***
//                addSafeZone(safeZoneCoordinates, 15, 20);
//                addSafeZone(safeZoneCoordinates, 50, 100);
//                break;
//            case BLUE:
//                addSafeZone(safeZoneCoordinates, 3, 5);
//                addSafeZone(safeZoneCoordinates, 12, 45);
//                addSafeZone(safeZoneCoordinates, 23, 35);
//                addSafeZone(safeZoneCoordinates, 34, 55);
//                break;
//            case GREEN:
//                addSafeZone(safeZoneCoordinates, 1, 11);
//                addSafeZone(safeZoneCoordinates, 12, 54);
//                addSafeZone(safeZoneCoordinates, 21, 31);
//                addSafeZone(safeZoneCoordinates, 32, 42);
//                break;
//            case YELLOW:
//                addSafeZone(safeZoneCoordinates, 2, 5);
//                addSafeZone(safeZoneCoordinates, 18, 23);
//                addSafeZone(safeZoneCoordinates, 28, 38);
//                addSafeZone(safeZoneCoordinates, 38, 48);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid team color: " + teamColor);
//        }
//        safeZone.put(teamColor, safeZoneCoordinates);
//    }
//
//    private void addSafeZone(List<Map<String, Integer>> teamSafeZones, int x, int y) {
//        Map<String, Integer> position = new HashMap<>();
//        position.put("x", x);
//        position.put("y", y);
//        teamSafeZones.add(position);
//    }
//
//    // Method to move the token forward *** player would call this method and pass in themselves THEN opponent ***
//    public void moveForward(String tokenId, int die, Player currentPlayer, Player opponentPlayer) {
//        int movesLeft;
//        Map<String, Integer> position = positions.get(tokenId);
//        if (position == null) {
//            System.out.println("Token " + tokenId + " does not exist.");
//            return;
//        }
//        int x = position.get("x");  // gets current position of token
//        int y = position.get("y");
//
//        for (int i = 1; i <= die; i++) {
//            // moving the token
//            int newX = x;
//            int newY = y + i;  // could be x + i, depending on token and where it is on board
//            movesLeft = die - 1;
//
//            // Update position based on die roll
//            updateTokenPosition(tokenId, newX, newY);
//
//            // Check if the new position matches player2's token position and send them to jail
//            if (positions.get(tokenId).equals(opponentPlayer.token.positions.get("token1"))) {
//                opponentPlayer.token.goToJail("token1");
//            }
//            else if (positions.get(tokenId).equals(opponentPlayer.token.positions.get("token2"))) {
//                opponentPlayer.token.goToJail("token2");
//            }
//            else if (safeZone.containsKey(tokenTeamColor)) {
//                Map<String, Integer> currentPosition = positions.get(tokenId); // gets current token position
//                int currX = currentPosition.get("x");
//                int currY = currentPosition.get("y");
//
//                while(!teamSafeSpace) {
//                    // gets safezone coordinates for token color and iterates over to see
//                    // if any safe zone coordinates match curr position call changeDirection()
//                    List<Map<String, Integer>> teamColorSafeZones = safeZone.get(tokenTeamColor);
//                    for (Map<String, Integer> safeZonePosition : teamColorSafeZones) {
//                        int safeX = safeZonePosition.get("x");
//                        int safeY = safeZonePosition.get("y");
//                        if (currX == safeX && currY == safeY) {
//                            teamSafeSpace = true;
//                            changeDirection(tokenId, movesLeft, position);
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//    }
//    private Tile currentTokenLocationOnTile;
//    //int currentTokenLocation; // store current token location for player
//
//    //AB:
//    //accessor method for currentLocation using Tile class
//
//
//    public Tile getCurrentTokenLocationOnTile() {
//        return currentTokenLocationOnTile;
//    }
//
//    public void setCurrentTokenLocation(Tile currentTokenLocationOnTile) {
//        this.currentTokenLocationOnTile = currentTokenLocationOnTile;
//    }
//
//    public void goToJail(String tokenId) {
//        // Reset the position of token to whatever the starting jail position is
//        updateTokenPosition(tokenId, 0, 0);
//    }
//
//    public void changeDirection(String tokenId, int movesLeft, Map<String, Integer> position){
//        //TODO change direction to safe zone towards home when safe space triggered
//        Map<String, Integer> currPosition = positions.get(tokenId);
//        int x = currPosition.get("x");  // gets current position of token
//        int y = currPosition.get("y");
//         // moving the token
//         int newX = x; // could be x + 1 depending on token and where it is on board
//         int newY = y + 1;
//         updateTokenPosition(tokenId, newX, newY);
//    }
//
//    public void goHome(){
//        //TODO move token piece to safe zone
//        /*
//         *  IF condition: space == teamSafeSpace
//         *      {move forward}
//         */
//    }
//
//    private void addToken(String tokenId, int x, int y) { // Creates map for x and y coordinate to map under tokenX
//        Map<String, Integer> position = new HashMap<>();
//        position.put("x", x);
//        position.put("y", y);
//        positions.put(tokenId, position);
//    }
//
//    // update the position of a token
//    public void updateTokenPosition(String tokenId, int newX, int newY) {
//        Map<String, Integer> position = positions.get(tokenId); // UPDATES map for x and y coordinate for map under tokenX
//        if (position != null) {
//            position.put("x", newX);
//            position.put("y", newY);
//        } else {
//            System.out.println("Token " + tokenId + " does not exist.");
//        }
//    }
//
//    // get position of tokenX map
//    public Map<String, Integer> getTokenPosition(String tokenId) {
//        return positions.get(tokenId);
//    }
//}

package components;


import com.game.components.board.Coordinate;
import com.game.utilities.TeamColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.game.utilities.TeamColor.*;

public class Token extends JComponent {
    private TeamColor teamColor;
    private Coordinate currentLocation;
    private boolean inJail;
    private Map<String, Map<String, Integer>> positions;
    private Map<TeamColor, List<Map<String, Integer>>> safeZone;

    public Token(TeamColor teamColor) {
        this.teamColor = teamColor;
        this.inJail = true;
        this.positions = new HashMap<>();
        this.safeZone = new HashMap<>();
        initializeSafeZone(teamColor);
    }

    public Token(TeamColor playerColor, int height, int width) {
    }

    public Coordinate getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Coordinate currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    private void initializeSafeZone(TeamColor teamColor) {
        // Initialize safe zones based on team color (Implementation similar to your provided code)
    }

    public void updateTokenPosition(String tokenId, int newX, int newY) {
        Map<String, Integer> position = positions.get(tokenId);
        if (position != null) {
            position.put("x", newX);
            position.put("y", newY);
        } else {
            System.out.println("Token " + tokenId + " does not exist.");
        }
    }

    public Map<String, Integer> getTokenPosition(String tokenId) {
        return positions.get(tokenId);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        switch (teamColor) {
            case RED:
                g2d.setColor(Color.RED);
                break;
            case BLUE:
                g2d.setColor(Color.BLUE);
                break;
            case GREEN:
                g2d.setColor(Color.GREEN);
                break;
            case YELLOW:
                g2d.setColor(Color.YELLOW);
                break;
        }

        g2d.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(5, 5, getWidth() - 10, getHeight() - 10);

        g2d.dispose();
    }
}

