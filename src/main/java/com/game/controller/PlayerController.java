
package com.game.controller;

import com.game.components.board.Coordinate;
import com.game.components.board.TeamPath;
import com.game.utilities.TeamColor;


import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerController {
    private Map<TeamColor, components.Token[]> players;

    public Map<TeamColor, components.Token[]> getPlayers() {
        return players;
    }

    public PlayerController(List<TeamPath> teamPaths) {
        players = new HashMap<>();
        for (TeamPath teamPath : teamPaths) {
            TeamColor teamColor = TeamColor.valueOf(teamPath.getTeamColor().toUpperCase());
            components.Token[] tokens = new components.Token[4];
            for (int i = 0; i < 4; i++) {
                tokens[i] = new components.Token(teamColor);
                Coordinate jailZone = teamPath.getJailZone().get(i);
                tokens[i].setCurrentLocation(jailZone);
            }
            players.put(teamColor, tokens);
        }
    }

    public void drawPlayers(Graphics2D g) {
        for (components.Token[] tokens : players.values()) {
            for (components.Token token : tokens) {
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


    class Player {
        private String name;
        private TeamColor playerColor;
        private components.Token[] tokens;
        private int ranking;
        private boolean win = false;

        public Player(String name, TeamColor playerColor, int tokenCount, int height, int width) {
            this.name = name;
            this.playerColor = playerColor;
            this.tokens = new components.Token[tokenCount];
            for (int i = 0; i < tokenCount; i++) {
                tokens[i] = new components.Token(playerColor, height, width);
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

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public components.Token[] getTokens() {
            return tokens;
        }

        public String toString() {
            return "Player: name is: " + name + "\nplayerColor is: " + playerColor;
        }
    }
}

