

package utilities;

import components.Token;
import tile.Coordinate;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerController {
    private Map<TeamColor, Token[]> players;

    public Map<TeamColor, Token[]> getPlayers() {
        return players;
    }

    public PlayerController(List<TeamPath> teamPaths) {
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

        public int getRanking() {
            return ranking;
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





//package utilities;
//
//
//import components.Token;
//import tile.Coordinate;
//
//import java.awt.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class PlayerController {
//    private Map<TeamColor, Player> players;
//    private int currentPlayerIndex;
//
//    public PlayerController(List<TeamPath> teamPaths) {
//        players = new HashMap<>();
//        for (TeamPath teamPath : teamPaths) {
//            Player player = new Player("Player " + teamPath.getTeamColor(), TeamColor.valueOf(teamPath.getTeamColor()));
//            players.put(TeamColor.valueOf(teamPath.getTeamColor()), player);
//            initializeTokens(player, teamPath.getJailZone());
//        }
//        currentPlayerIndex = 0;
//    }
//
//    private void initializeTokens(Player player, List<Coordinate> jailZone) {
//        for (int i = 0; i < player.getTokens().length; i++) {
//            Token token = player.getTokens()[i];
//            token.setCurrentLocation(jailZone.get(i));
//        }
//    }
//
//    public void drawPlayers(Graphics2D g) {
//        for (Player player : players.values()) {
//            for (Token token : player.getTokens()) {
//                Coordinate location = token.getCurrentLocation();
//                if (location != null) {
//                    token.setBounds(location.getX() * 60, location.getY() * 60, 60, 60);
//                    token.repaint();
//                    g.translate(location.getX() * 60, location.getY() * 60);
//                    token.paint(g);
//                    g.translate(-location.getX() * 60, -location.getY() * 60);
//                }
//            }
//        }
//    }
//
//    public Player getCurrentPlayer() {
//        TeamColor[] teamColors = players.keySet().toArray(new TeamColor[0]);
//        return players.get(teamColors[currentPlayerIndex]);
//    }
//
//    public void nextPlayer() {
//        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
//    }
//
//    public void moveToken(Player player, Token token, int steps) {
//        Coordinate currentLocation = token.getCurrentLocation();
//        // Update the token's location based on the steps
//        int newX = currentLocation.getX() + steps;
//        int newY = currentLocation.getY();
//        token.setCurrentLocation(new Coordinate(newX, newY));
//    }
//
//    public void reset() {
//        for (Player player : players.values()) {
//            for (Token token : player.getTokens()) {
//                token.setInJail(true);
//                // Reset the token's position to the jail zone
//                token.setCurrentLocation(getInitialLocation(player.getPlayerColor()));
//            }
//        }
//    }
//
//    private Coordinate getInitialLocation(TeamColor teamColor) {
//        switch (teamColor) {
//            case RED:
//                return new Coordinate(1, 6);
//            case GREEN:
//                return new Coordinate(8, 1);
//            case BLUE:
//                return new Coordinate(6, 13);
//            case YELLOW:
//                return new Coordinate(13, 8);
//            default:
//                return null;
//        }
//    }
//
//    public int getPlayerCoins(int player) {
//        // Add logic to get the number of coins the player has
//        return 0;
//    }
//
//    public boolean canMove(int playerIndex, int tokenIndex, int diceValue) {
//        Token token = getPlayerToken(playerIndex, tokenIndex);
//        if (token == null || token.isInJail()) return false;
//        // Add logic to check if the token can move based on the dice value
//        return true;
//    }
//
//    public boolean isTokenInJail(int playerIndex, int tokenIndex) {
//        Token token = getPlayerToken(playerIndex, tokenIndex);
//        return token != null && token.isInJail();
//    }
//
//    public boolean moveToken(int playerIndex, int gridX, int gridY, int diceValue) {
//        Token[] tokens = players.get(getTeamColor(playerIndex)).getTokens();
//        for (Token token : tokens) {
//            Coordinate location = token.getCurrentLocation();
//            if (location.getX() == gridX && location.getY() == gridY) {
//                // Add logic to move the token based on the dice value
//                token.setCurrentLocation(new Coordinate(gridX + diceValue, gridY));
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Token getPlayerToken(int playerIndex, int tokenIndex) {
//        TeamColor teamColor = getTeamColor(playerIndex);
//        Token[] tokens = players.get(teamColor).getTokens();
//        return tokens != null ? tokens[tokenIndex] : null;
//    }
//
//    private TeamColor getTeamColor(int playerIndex) {
//        switch (playerIndex) {
//            case 0:
//                return TeamColor.RED;
//            case 1:
//                return TeamColor.GREEN;
//            case 2:
//                return TeamColor.YELLOW;
//            case 3:
//                return TeamColor.BLUE;
//            default:
//                return null;
//        }
//    }
//}
