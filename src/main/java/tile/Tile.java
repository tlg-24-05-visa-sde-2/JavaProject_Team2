package tile;

import components.Token;

public class Tile {
    private Coordinate location;
    private Token occupyingToken;
    private boolean isPathTile;
    // Constructor

    public Tile(int x, int y) {
        this.location = new Coordinate(x, y);
    }

    // Getters
    public boolean isPathTile() {
        return isPathTile;
    }

    public void setPathTile(boolean isPathTile) {
        this.isPathTile = isPathTile;
    }
    public Coordinate getLocation() {
        return location;
    }

    public Token getOccupyingToken() {
        return occupyingToken;
    }

    public void setOccupyingToken(Token token) {
        this.occupyingToken = token;
    }

    public boolean isOccupied() {
        return occupyingToken != null;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "location=" +getLocation() +
                ", occupyingToken=" + getOccupyingToken() +
                ", isPathTile=" + getOccupyingToken() +
                '}';
    }
}