package utilities;

import tile.Coordinate;

import java.awt.*;
import java.util.List;

public class Player {
    int height,width,status,coin;
    private TeamColor teamColor;

    public Pawn[] getPawns() {
        return pawns;
    }

    public Pawn[] pawns = new Pawn[2];
    private int coinCount;

    public Player(TeamColor teamColor, Coordinate startingPosition) {
        this.teamColor = teamColor;
        this.coinCount = 0;
        initializePawns(startingPosition);
    }

    public Player(int height, int width) {
        this();
        this.height = height;
        this.width = width;
        status = -1;
    } 

    public Player() {
        
    }

    private void initializePawns(Coordinate startingPosition) {
        pawns = new Pawn[4];
        for (int i = 0; i < 4; i++) {
            pawns[i] = new Pawn(startingPosition.getX(), startingPosition.getY());
        }
    }

    public void draw(Graphics2D g) {
        for (Pawn pawn : pawns) {
            pawn.draw(g, teamColor);
        }
    }

    public Pawn getPawn(int index) {
        return pawns[index];
    }

    public int getPawnIndexAt(int x, int y) {
        for (int i = 0; i < pawns.length; i++) {
            if (pawns[i].getX() == x && pawns[i].getY() == y) {
                return i;
            }
        }
        return -1;
    }

    public void movePawn(int index, int steps) {
        pawns[index].moveForward(steps);
    }

    public void moveOutOfStart() {
        for (Pawn pawn : pawns) {
            if (pawn.getCurrentPosition() == -1) {
                pawn.moveForward(1);
                break;
            }
        }
    }

    public boolean hasWon() {
        return coinCount == 4;
    }

    public void incrementCoinCount() {
        coinCount++;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public static class Pawn {
        private int x, y;
        public int currentPosition;
        private int height = 30, width = 30;

        public Pawn(int x, int y) {
            this.x = x;
            this.y = y;
            this.currentPosition = -1;
        }

        public void draw(Graphics2D g, TeamColor teamColor) {
            if (currentPosition == -1) {
                g.setColor(teamColor.getColor());
                g.fillOval(80 + 5 + (x * width), 50 + 5 + (y * height), width - 10, height - 10);
                g.setStroke(new BasicStroke(2));
                g.setColor(Color.BLACK);
                g.drawOval(80 + 5 + (x * width), 50 + 5 + (y * height), width - 10, height - 10);
            } else {
                x = Path.ax[teamColor.ordinal()][currentPosition];
                y = Path.ay[teamColor.ordinal()][currentPosition];
                g.setColor(teamColor.getColor());
                g.fillOval(80 + 5 + (x * width), 50 + 5 + (y * height), width - 10, height - 10);
                g.setStroke(new BasicStroke(2));
                g.setColor(Color.BLACK);
                g.drawOval(80 + 5 + (x * width), 50 + 5 + (y * height), width - 10, height - 10);
            }
        }

        public int getCurrentPosition() {
            return currentPosition;
        }

        public void setCurrentPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        public void moveForward(int steps) {
            currentPosition += steps;
        }

        public void resetPosition() {
            currentPosition = -1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void draw(Graphics2D g, int i, int j,int play) {
            if(currentPosition==-1) { // If pawn is not on board
                int temp1=80+(height/2),temp2=50+(width/2); // Initial position of pawn
                x=i; // x coordinate of pawn
                y=j; // y coordinate of pawn
                if(play==0) { // If player 1
                    g.setColor(Color.RED); // Color of pawn
                }
                else if(play==1) { // If player 2
                    g.setColor(Color.GREEN); // Color of pawn
                }
                else if(play==2) { // If player 3
                    g.setColor(Color.YELLOW); // Color of pawn
                }
                else if(play==3) { // If player 4
                    g.setColor(Color.BLUE); // Color of pawn
                }
                g.fillOval(temp1+5+(i*width),temp2+5+(j*height),width-10,height-10); // Drawing pawn on board
                g.setStroke(new BasicStroke(2)); // Thickness of border
                g.setColor(Color.BLACK); // Color of border
                g.drawOval(temp1+5+(i*width),temp2+5+(j*height),width-10,height-10); // Drawing border of pawn
            }
            else
            { // If pawn is on board
                int temp1=80,temp2=50; // Initial position of board
                x=Path.ax[play][currentPosition]; // x coordinate of pawn
                y=Path.ay[play][currentPosition]; // y coordinate of pawn
                if(play==0) {
                    g.setColor(Color.RED);
                }
                else if(play==1) {
                    g.setColor(Color.GREEN);
                }
                else if(play==2) {
                    g.setColor(Color.YELLOW);
                }
                else if(play==3) {
                    g.setColor(Color.BLUE);
                }
                g.fillOval(temp1+5+(x*width),temp2+5+(y*height),width-10,height-10); // Drawing pawn on board
                g.setStroke(new BasicStroke(2)); // Thickness of border
                g.setColor(Color.BLACK);
                g.drawOval(temp1+5+(x*width),temp2+5+(y*height),width-10,height-10); // Drawing border of pawn
            }
        }


    }

}

