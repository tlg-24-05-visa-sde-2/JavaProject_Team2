//package utilities;
//
//import javax.swing.*;
//
//
//import java.awt.*;
//import java.awt.event.*;
//import java.util.Random;
//
//public class TokenMoves extends JPanel implements KeyListener, ActionListener, MouseListener {
//
//    private BoardPanel boardPanel; // Object for the BoardPanel class
//    private PlayerController playerController; // Object for the PlayerController class
//    private Timer timer; // Object for the Timer class
//    private int timerDelay = 10; // Delay for the timer
//
//    private int currentPlayerIndex; // Variable for the current player index
//    private int diceRollResult; // Variable for the dice roll result
//    private boolean canRollDice; // Flag to check if the dice can be rolled
//    private boolean hasRolledSix; // Flag to check if the player has rolled a six
//    private boolean hasKilledOpponent; // Flag to check if a player has killed an opponent's token
//
//    public TokenMoves(PathController pathController) {
//        setFocusTraversalKeysEnabled(false);
//        requestFocus();
//        currentPlayerIndex = 0; // First player is set to red
//        boardPanel = new BoardPanel(new PathController("/boardpath.json"));
//        playerController = new PlayerController(boardPanel.getTeamPaths());
//        diceRollResult = 0;
//        canRollDice = false;
//        hasRolledSix = false;
//        hasKilledOpponent = false;
//    }
//
//    // Method for drawing the game
//    public void paint(Graphics g) {
//        boardPanel.paint((Graphics2D) g);
//        playerController.drawPlayers((Graphics2D) g);
//
//        if (PlayerController.getPlayer(currentPlayerIndex) == 4) { // Condition for the winner
//            g.setColor(Color.WHITE);
//            g.fillRect(590, 100, 380, 130);
//
//            switch (currentPlayerIndex) {
//                case 0:
//                    g.setColor(Color.RED);
//                    break;
//                case 1:
//                    g.setColor(Color.GREEN);
//                    break;
//                case 2:
//                    g.setColor(Color.YELLOW);
//                    break;
//                case 3:
//                    g.setColor(Color.BLUE);
//                    break;
//            }
//
//            g.setFont(new Font("Serif", Font.BOLD, 40));
//            g.drawString("Player " + (currentPlayerIndex + 1) + " wins.", 600, 350);
//            g.drawString("Congratulations.", 600, 400);
//
//            currentPlayerIndex = 0;
//            boardPanel = new BoardPanel(new PathController("/boardpath.json"));
//            playerController = new PlayerController(boardPanel.getTeamPaths());
//            diceRollResult = 0;
//            canRollDice = false;
//            hasRolledSix = false;
//            hasKilledOpponent = false;
//        } else if (diceRollResult != 0) { // Condition for the dice roll
//            g.setColor(Color.PINK);
//            g.fillRect(590, 300, 260, 200);
//
//            switch (currentPlayerIndex) {
//                case 0:
//                    g.setColor(Color.RED);
//                    break;
//                case 1:
//                    g.setColor(Color.GREEN);
//                    break;
//                case 2:
//                    g.setColor(Color.YELLOW);
//                    break;
//                case 3:
//                    g.setColor(Color.BLUE);
//                    break;
//            }
//
//            g.setFont(new Font("MV Boli", Font.BOLD, 30));
//            g.drawString(new StringBuilder().append(playerController.getPlayer(currentPlayerIndex)).append("'s turn:").toString(), 600, 350);
//
//            // Draw dice
//            g.setColor(Color.BLACK);
//            g.drawRect(670, 370, 100, 100);
//            g.setFont(new Font("MV Boli", Font.BOLD, 50));
//            g.drawString("" + diceRollResult, 700, 440);
//        }
//
//        if (!canRollDice && diceRollResult != 0 && diceRollResult != 6 && !hasKilledOpponent) {
//            currentPlayerIndex = (currentPlayerIndex + 1) % 4;
//        }
//
//        hasKilledOpponent = false;
//    }
//
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER && !canRollDice) { // If enter is pressed the dice is rolled
//            Random rand = new Random();
//            diceRollResult = 1 + rand.nextInt(6); // Random number is generated for the dice roll
//            repaint();
//
//            for (int i = 0; i < 4; i++) {
//                if (playerController.getPlayer(currentPlayerIndex) != -1 &&
//                        playerController.getPlayer(currentPlayerIndex) != 56 &&
//                        (playerController.getPlayer(currentPlayerIndex) + diceRollResult) <= 56) {
//                    canRollDice = true;
//                    break;
//                }
//            }
//
//            if (!canRollDice && diceRollResult == 6) {
//                for (int i = 0; i < 4; i++) {
//                    if (playerController.getPlayer(currentPlayerIndex) == -1) {
//                        canRollDice = true;
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    public void mouseClicked(MouseEvent e) {
//        if (canRollDice) {
//            int mouseX = e.getX();
//            int mouseY = e.getY();
//            mouseX = (mouseX - 80) / 30;
//            mouseY = (mouseY - 50) / 30;
//            int selectedPawnIndex = -1;
//
//            if (diceRollResult == 6) { // If dice is 6
//                for (int i = 0; i < 4; i++) {
//                    if (playerController.getPlayer(currentPlayerIndex) == mouseX &&
//                            playerController.getPlayer(currentPlayerIndex) == mouseY &&
//                            (playerController.getPlayer(currentPlayerIndex) + diceRollResult) <= 56) {
//                        selectedPawnIndex = i;
//                        canRollDice = false;
//                        break;
//                    }
//                }
//
//                if (selectedPawnIndex != -1) { // If the player has a coin on the clicked position
//                    PlayerController.getPlayer(currentPlayerIndex).moveForward(diceRollResult);
//                    if (PlayerController.getPlayer(currentPlayerIndex) == 56) {
//                        PlayerController.getPlayer(currentPlayerIndex).incrementCoinCount();
//                    }
//
//                    int killCheck = 0;
//                    int currentPawnPosition = playerController.getPlayer(currentPlayerIndex).getCurrentPosition();
//
//                    if ((currentPawnPosition % 13) != 0 && (currentPawnPosition % 13) != 8 && currentPawnPosition < 51) {
//                        for (int i = 0; i < 4; i++) {
//                            if (i != currentPlayerIndex) {
//                                for (int j = 0; j < 4; j++) {
//                                    int opponentX = Path.ax[currentPlayerIndex][playerController.getPlayer(currentPlayerIndex)];
//                                    int opponentY = Path.ay[currentPlayerIndex][playerController.getPlayer(currentPlayerIndex)];
//
//                                    if (playerController.getPlayer(i) == opponentX &&
//                                            playerController.getPlayer(i) == opponentY) {
//                                        playerController.getPlayer(i).resetPosition();
//                                        hasKilledOpponent = true;
//                                        killCheck = 1;
//                                        break;
//                                    }
//                                }
//                            }
//
//                            if (killCheck == 1)
//                                break;
//                        }
//                    }
//                } else { // If the player does not have a coin on the clicked position
//                    for (int i = 0; i < 4; i++) {
//                        if (playerController.getPlayer(currentPlayerIndex) == -1) {
//                            playerController.getPlayer(currentPlayerIndex).moveForward(0);
//                            canRollDice = false;
//                            break;
//                        }
//                    }
//                }
//            } else { // If dice is not 6
//                for (int i = 0; i < 4; i++) {
//                    if (playerController.getPlayer(currentPlayerIndex) == mouseX &&
//                            playerController.getPlayer(currentPlayerIndex) == mouseY &&
//                            (playerController.getPlayer(currentPlayerIndex) + diceRollResult) <= 56) {
//                        selectedPawnIndex = i;
//                        canRollDice = false;
//                        break;
//                    }
//                }
//
//                if (selectedPawnIndex != -1) { // If the player has a coin on the clicked position
//                    playerController.getPlayer(currentPlayerIndex).moveForward(diceRollResult);
//                    if (playerController.getPlayer(currentPlayerIndex).getCurrentPosition() == 56) {
//                        playerController.getPlayer(currentPlayerIndex).incrementCoinCount();
//                    }
//
//                    int killCheck = 0;
//                    int currentPawnPosition = playerController.getPlayer(currentPlayerIndex).getCurrentPosition();
//
//                    if ((currentPawnPosition % 13) != 0 && (currentPawnPosition % 13) != 8 && currentPawnPosition < 51) {
//                        for (int i = 0; i < 4; i++) {
//                            if (i != currentPlayerIndex) {
//                                for (int j = 0; j < 4; j++) {
//                                    int opponentX = Path.ax[currentPlayerIndex][playerController.getPlayer(currentPlayerIndex)];
//                                    int opponentY = Path.ay[currentPlayerIndex][playerController.getPlayer(currentPlayerIndex).getCurrentPosition()];
//
//                                    if (playerController.getPlayer(i) == opponentX &&
//                                            playerController.getPlayer(i) == opponentY) {
//                                        playerController.getPlayer(i).getPawn(j).resetPosition();
//                                        hasKilledOpponent = true;
//                                        killCheck = 1;
//                                        break;
//                                    }
//                                }
//                            }
//
//                            if (killCheck == 1)
//                                break;
//                        }
//                    }
//                }
//            }
//
//            repaint();
//        }
//    }
//
//    // These functions are not used but are compulsory to be defined as they are abstract functions of MouseListener
//    public void actionPerformed(ActionEvent e) {
//    }
//
//    public void keyReleased(KeyEvent arg0) {
//    }
//
//    public void keyTyped(KeyEvent arg0) {
//    }
//
//    public void mouseEntered(MouseEvent arg0) {
//    }
//
//    public void mouseExited(MouseEvent arg0) {
//    }
//
//    public void mousePressed(MouseEvent e) {
//    }
//
//    public void mouseReleased(MouseEvent arg0) {
//    }
//}
//
//
////package utilities;
////
////import components.Token;
////import tile.Coordinate;
////import utilities.TeamColor;
////
////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.*;
////import java.util.HashMap;
////import java.util.List;
////import java.util.Map;
////
////public class TokenMovement extends JPanel implements KeyListener, ActionListener, MouseListener {
////    private static final long serialVersionUID = 1L;
////    private BoardPanel boardPanel;
////    private PlayerController playerController;
////    private int currentPlayerIndex;
////    private int diceValue;
////    private boolean flag;
////    private int roll;
////    private int kill;
////
////    public TokenMovement(BoardPanel boardPanel, PlayerController playerController) {
////        this.boardPanel = boardPanel;
////        this.playerController = playerController;
////        setFocusTraversalKeysEnabled(false);
////        requestFocus();
////        currentPlayerIndex = 0; // first player is set to red
////        diceValue = 0;
////        flag = false;
////        roll = 0;
////        kill = 0;
////
////        addMouseListener(this);
////    }
////
////    public void paint(Graphics g) {
////        super.paint(g);
////        playerController.drawPlayers((Graphics2D) g);
////        if (playerController.getPlayerCoins(currentPlayerIndex) == 1) { // This is the condition for the winner
////            g.setColor(Color.WHITE);
////            g.fillRect(590, 100, 380, 130);
////            g.setColor(getPlayerColor(currentPlayerIndex));
////            g.setFont(new Font("serif", Font.BOLD, 40));
////            g.drawString("Player " + (currentPlayerIndex + 1) + " wins.", 600, 350);
////            g.drawString("Congratulations.", 600, 400);
////            resetGame();
////        } else if (diceValue != 0) { // This is the condition for the dice roll
////            g.setColor(Color.PINK);
////            g.fillRect(590, 300, 260, 200);
////            g.setColor(getPlayerColor(currentPlayerIndex));
////            g.setFont(new Font("MV Boli", Font.BOLD, 30));
////            g.drawString(getPlayerColorName(currentPlayerIndex) + "'s turn:", 600, 350);
////            g.setColor(Color.BLACK);
////            g.drawRect(670, 370, 100, 100);
////            g.setFont(new Font("MV Boli", Font.BOLD, 50));
////            g.drawString("" + diceValue, 700, 440);
////        }
////        if (!flag && diceValue != 0 && diceValue != 6 && kill == 0) {
////            currentPlayerIndex = (currentPlayerIndex + 1) % 4;
////        }
////        kill = 0;
////    }
////
////    private void resetGame() {
////        currentPlayerIndex = 0;
////        playerController.reset();
////        diceValue = 0;
////        flag = false;
////        roll = 0;
////        kill = 0;
////        repaint();
////    }
////
////    private Color getPlayerColor(int playerIndex) {
////        switch (playerIndex) {
////            case 0:
////                return Color.RED;
////            case 1:
////                return Color.GREEN;
////            case 2:
////                return Color.YELLOW;
////            case 3:
////                return Color.BLUE;
////            default:
////                return Color.BLACK;
////        }
////    }
////
////    private String getPlayerColorName(int playerIndex) {
////        switch (playerIndex) {
////            case 0:
////                return "RED";
////            case 1:
////                return "GREEN";
////            case 2:
////                return "YELLOW";
////            case 3:
////                return "BLUE";
////            default:
////                return "";
////        }
////    }
////
////    public void keyPressed(KeyEvent e) {
////        if (e.getKeyCode() == KeyEvent.VK_ENTER && !flag) { // if enter is pressed the dice is rolled
////            roll = 0;
////            diceValue = 1 + (int) (Math.random() * 6); // random number is generated for the dice roll
////            repaint();
////            for (int i = 0; i < 4; i++) {
////                if (playerController.canMove(currentPlayerIndex, i, diceValue)) {
////                    flag = true;
////                    break;
////                }
////            }
////            if (!flag && diceValue == 6) {
////                for (int i = 0; i < 4; i++) {
////                    if (playerController.isTokenInJail(currentPlayerIndex, i)) {
////                        flag = true;
////                        break;
////                    }
////                }
////            }
////        }
////    }
////
////    public void mouseClicked(MouseEvent e) {
////        if (flag) {
////            int x = e.getX();
////            int y = e.getY();
////            int gridX = x / 60;
////            int gridY = y / 60;
////            if (diceValue == 6) { // if dice is 6
////                if (playerController.moveToken(currentPlayerIndex, gridX, gridY, diceValue)) {
////                    flag = false;
////                }
////            } else { // if dice is not 6
////                if (playerController.moveToken(currentPlayerIndex, gridX, gridY, diceValue)) {
////                    flag = false;
////                }
////            }
////            repaint();
////        }
////    }
////
////    public void actionPerformed(ActionEvent e) {
////    }
////
////    public void keyReleased(KeyEvent arg0) {
////    }
////
////    public void keyTyped(KeyEvent arg0) {
////    }
////
////    public void mouseEntered(MouseEvent arg0) {
////    }
////
////    public void mouseExited(MouseEvent arg0) {
////    }
////
////    public void mousePressed(MouseEvent e) {
////    }
////
////    public void mouseReleased(MouseEvent arg0) {
////    }
////}
//
