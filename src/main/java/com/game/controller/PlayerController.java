
package com.game.controller;

import ch.qos.logback.core.subst.Token;
import com.game.components.board.Coordinate;
import com.game.components.board.TeamPath;
import com.game.utilities.TeamColor;
import lombok.Getter;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

//public class PlayerController {
//    private Map<TeamColor, Token[]> players;
//
//    public Map<TeamColor, Token[]> getPlayers() {
//        return players;
//    }
//
//    public PlayerController(List<TeamPath> teamPaths) {
//        players = new HashMap<>();
//        for (TeamPath teamPath : teamPaths) {
//            TeamColor teamColor = TeamColor.valueOf(teamPath.getTeamColor().toUpperCase());
//            Token[] tokens = new Token[4];
//            for (int i = 0; i < 4; i++) {
//                tokens[i] = new Token(teamColor);
//                Coordinate jailZone = teamPath.getJailZone().get(i);
//                tokens[i].setCurrentLocation(jailZone);
//            }
//            players.put(teamColor, tokens);
//        }
//    }
//
//    public void drawPlayers(Graphics2D g) {
//        for (Token[] tokens : players.values()) {
//            for (Token token : tokens) {
//                Coordinate location = token.getCurrentLocation();
//                token.setBounds(location.getX() * 60, location.getY() * 60, 60, 60);
//                token.repaint();
//                g.translate(location.getX() * 60, location.getY() * 60);
//                token.paint(g);
//                g.translate(-location.getX() * 60, -location.getY() * 60);
//            }
//        }
//    }
//
//    public static int getPlayer(int currentPlayerIndex) {
//        return currentPlayerIndex;
//    }



//    package utilities;
//
//
//import components.Token;
//import utilities.TeamColor;
public class PlayerController {
    private Map<TeamColor, Token[]> players;

    public Map<TeamColor, Token[]> getPlayers() {
        return players;
    }

    public PlayerController(java.util.List<TeamPath> teamPaths) {
        players = new HashMap<>();
        for (TeamPath teamPath : teamPaths) {
            TeamColor teamColor = TeamColor.valueOf(teamPath.getTeamColor().toUpperCase());
            Token[] tokens = new Token[4];
            for (int i = 0; i < 4; i++) {
                tokens[i] = new Token(teamColor);
                Coordinate jailZone = teamPath.getJailZone().get(i);
                tokens[i].setCurrentLocation(jailZone);
            }
            players.put(teamColor, tokens);
        }
    }

    public void drawPlayers(Graphics2D g) {
        for (Token[] tokens : players.values()) {
            for (Token token : tokens) {
                Coordinate location = token.getCurrentLocation();
                token.setBounds(location.getX() * 60, location.getY() * 60, 60, 60);
                token.repaint();
                g.translate(location.getX() * 60, location.getY() * 60);
                token.paint(g);
                g.translate(-location.getX() * 60, -location.getY() * 60);
            }
        }
    }

    public static int getPlayer(int currentPlayerIndex) {
        return currentPlayerIndex;
    }



//    package utilities;
//
//
//import components.Token;
//import utilities.TeamColor;

    class Player {
        private String name;
        private TeamColor playerColor;
        private Token[] tokens;
        @Getter
        private int ranking;
        private boolean win = false;

        public Player(String name, TeamColor playerColor, int tokenCount, int height, int width) {
            this.name = name;
            this.playerColor = playerColor;
            this.tokens = new Token[tokenCount];
            for (int i = 0; i < tokenCount; i++) {
                tokens[i] = new Token(playerColor, height, width);
            }
        }

        public Player(String name, TeamColor playerColor) {
        }

        public void win() {
            // Implement the logic to check if the player has won
        }

        public String getName() {
            return name;
        }

        public TeamColor getPlayerColor() {
            return playerColor;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public Token[] getTokens() {
            return tokens;
        }

        public String toString() {
            return "Player: name is: " + name + "\nplayerColor is: " + playerColor;
        }
    }
}
