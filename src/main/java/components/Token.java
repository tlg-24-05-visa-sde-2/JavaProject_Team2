package components;

import tile.Coordinate;
import utilities.TeamColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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





//package components;
//
//import tile.Coordinate;
//import utilities.TeamColor;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class Token extends JComponent {
//    private TeamColor teamColor;
//    private Coordinate currentLocation;
//    private int current;
//    private int height, width;
//    private boolean inJail;
//
//    public Token(TeamColor teamColor, int height, int width) {
//        this.teamColor = teamColor;
//        this.height = height;
//        this.width = width;
//        this.current = -1;
//        this.inJail = true;
//    }
//
//    public Coordinate getCurrentLocation() {
//        return currentLocation;
//    }
//
//    public void setCurrentLocation(Coordinate currentLocation) {
//        this.currentLocation = currentLocation;
//    }
//
//    public boolean isInJail() {
//        return inJail;
//    }
//
//    public void setInJail(boolean inJail) {
//        this.inJail = inJail;
//    }
//
//    public TeamColor getTeamColor() {
//        return teamColor;
//    }
//
//    public void move(int steps) {
//        // Add logic to move the token based on the number of steps
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setColor(getColor());
//        g2d.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
//        g2d.setStroke(new BasicStroke(2));
//        g2d.setColor(Color.BLACK);
//        g2d.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
//        g2d.dispose();
//    }
//
//    private Color getColor() {
//        switch (teamColor) {
//            case RED:
//                return Color.RED;
//            case BLUE:
//                return Color.BLUE;
//            case GREEN:
//                return Color.GREEN;
//            case YELLOW:
//                return Color.YELLOW;
//            default:
//                return Color.BLACK;
//        }
//    }
//}
