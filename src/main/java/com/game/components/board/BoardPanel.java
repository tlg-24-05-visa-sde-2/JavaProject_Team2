package com.game.components.board;

import com.game.components.Die;
import com.game.controller.PathController;
import com.game.controller.PlayerController;
import com.game.gui.RuleWindow;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BoardPanel extends JPanel {
    public static final int TILE_SIZE = 60;
    public static final int BOARD_SIZE = 15;
    private BufferedImage backgroundImage;
    private JLabel diceResultLabel;
    private Board gameBoard;
    private Tile[][] tiles;
    private PlayerController playerController;
    private List<Coordinate> boardPath;
    private List<TeamPath> teamPaths;

    public BoardPanel(PathController pathController) {
        this.boardPath = pathController.getBoardPath();
        this.teamPaths = pathController.getTeamPaths();
        initializeBoard(pathController);
    }

    private void initializeBoard(PathController pathController) {
        if (boardPath == null || teamPaths == null) {
            System.err.println("Error: Failed to load paths.");
            return;
        }
        loadBackgroundImage();
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));

        initializeBoardComponents(pathController);
        initializePlayerController(pathController);

        setLayout(new BorderLayout());

        JPanel gridPanel = new GridPanel();
        gridPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                GridButton button = new GridButton(i, j);
                button.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                button.setBorderPainted(false);
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleGridClick(button.x, button.y);
                    }
                });
                gridPanel.add(button);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton button1 = new JButton("Help");
        JButton button2 = new JButton("Roll Dice");
        //JButton button3 = new JButton("Button 3");

        button1.addActionListener(e -> new RuleWindow());

        button2.addActionListener(e -> {
            Die die = new Die();
            int rollResult = die.rollDice();
            diceResultLabel.setText("Rolled: " + rollResult);
            Timer timer = new Timer(2000, event -> diceResultLabel.setText(""));
            timer.setRepeats(false);
            timer.start();
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        //buttonPanel.add(button3);

        add(buttonPanel, BorderLayout.SOUTH);

        diceResultLabel = new JLabel("", SwingConstants.CENTER);
        add(diceResultLabel, BorderLayout.NORTH);

        setFocusable(true);
        requestFocusInWindow();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/board.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeBoardComponents(PathController pathController) {
        List<Coordinate> trackPath = pathController.getBoardPath();
        List<TeamPath> teamPaths = pathController.getTeamPaths();
        gameBoard = new Board(BOARD_SIZE, teamPaths, trackPath);
        gameBoard.initializeBoard(BOARD_SIZE, teamPaths, trackPath);
        tiles = gameBoard.getTiles();
    }

    private void initializePlayerController(PathController pathController) {
        playerController = new PlayerController(List.copyOf(pathController.getTeamPaths()));
    }

    private void handleGridClick(int x, int y) {
        System.out.printf("{\"x\": %d, \"y\": %d}%n", x, y);
        Tile clickedTile = gameBoard.getTile(x, y);
        if (clickedTile != null && clickedTile.isOccupied()) {
            components.Token occupyingToken = (components.Token) clickedTile.getOccupyingToken();
            System.out.println("Tile is occupied by token of team: " + occupyingToken.getTeamColor());
        }
    }

    private class GridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE, this);
            }

            drawBoard((Graphics2D) g);
            playerController.drawPlayers((Graphics2D) g);
        }

        private void drawBoard(Graphics2D g) {
            int x = 0, y = 0, width = TILE_SIZE, height = TILE_SIZE;

            g.setColor(Color.WHITE);
            g.fillRect(x, y, 15 * width, 15 * height);

            for (int i = 0; i < 6; i++) {
                g.setColor(Color.RED);
                g.fillRect(x + (i * width), y, width, height);
                g.fillRect(x, y + (i * height), width, height);
                g.fillRect(x + (i * width), y + (5 * height), width, height);
                g.fillRect(x + (5 * width), y + (i * height), width, height);
                g.setColor(Color.GREEN);
                g.fillRect(x + ((i + 9) * width), y, width, height);
                g.fillRect(x + (9 * width), y + (i * height), width, height);
                g.fillRect(x + ((i + 9) * width), y + (5 * height), width, height);
                g.fillRect(x + (14 * width), y + (i * height), width, height);
                g.setColor(Color.YELLOW);
                g.fillRect(x + ((i + 9) * width), y + (9 * height), width, height);
                g.fillRect(x + (9 * width), y + ((i + 9) * height), width, height);
                g.fillRect(x + ((i + 9) * width), y + (14 * height), width, height);
                g.fillRect(x + (14 * width), y + ((i + 9) * height), width, height);
                g.setColor(Color.BLUE);
                g.fillRect(x + (i * width), y + (9 * height), width, height);
                g.fillRect(x, y + ((i + 9) * height), width, height);
                g.fillRect(x + (i * width), y + (14 * height), width, height);
                g.fillRect(x + (5 * width), y + ((i + 9) * height), width, height);
            }

            for (int i = 1; i < 6; i++) {
                g.setColor(Color.RED);
                g.fillRect(x + (i * width), y + (7 * height), width, height);
                g.setColor(Color.YELLOW);
                g.fillRect(x + ((8 + i) * width), y + (7 * height), width, height);
                g.setColor(Color.GREEN);
                g.fillRect(x + (7 * width), y + (i * height), width, height);
                g.setColor(Color.BLUE);
                g.fillRect(x + (7 * width), y + ((8 + i) * height), width, height);
            }

            g.setColor(Color.RED);
            g.fillRect(x + (1 * width), y + (6 * height), width, height);
            g.setColor(Color.YELLOW);
            g.fillRect(x + (13 * width), y + (8 * height), width, height);
            g.setColor(Color.GREEN);
            g.fillRect(x + (8 * width), y + (1 * height), width, height);
            g.setColor(Color.BLUE);
            g.fillRect(x + (6 * width), y + (13 * height), width, height);

            g.setColor(Color.RED);
            int[] xpoints0 = {x + (6 * width), x + (6 * width), x + 15 + (7 * width)};
            int[] ypoints0 = {y + (6 * height), y + (9 * height), y + 15 + (7 * width)};
            int npoints = 3;
            g.fillPolygon(xpoints0, ypoints0, npoints);

            g.setColor(Color.YELLOW);
            int[] xpoints1 = {x + (9 * width), x + (9 * width), x + 15 + (7 * width)};
            int[] ypoints1 = {y + (6 * height), y + (9 * height), y + 15 + (7 * width)};
            int npoints1 = 3;
            g.fillPolygon(xpoints1, ypoints1, npoints1);

            g.setColor(Color.GREEN);
            int[] xpoints2 = {x + (6 * width), x + (9 * width), x + 15 + (7 * width)};
            int[] ypoints2 = {y + (6 * height), y + (6 * height), y + 15 + (7 * width)};
            int npoints2 = 3;
            g.fillPolygon(xpoints2, ypoints2, npoints2);

            g.setColor(Color.BLUE);
            int[] xpoints3 = {x + (6 * width), x + (9 * width), x + 15 + (7 * width)};
            int[] ypoints3 = {y + (9 * height), y + (9 * height), y + 15 + (7 * width)};
            int npoints3 = 3;
            g.fillPolygon(xpoints3, ypoints3, npoints3);

            g.setStroke(new BasicStroke(2));
            g.setColor(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 6; j++) {
                    g.drawRect(x + ((i + 6) * width), y + (j * height), width, height);
                    g.drawRect(x + (j * width), y + ((i + 6) * height), width, height);
                    g.drawRect(x + ((i + 6) * width), y + ((j + 9) * height), width, height);
                    g.drawRect(x + ((j + 9) * width), y + ((i + 6) * height), width, height);
                }
            }

            g.drawRect(x + (1 * width), y + (1 * height), 4 * width, 4 * height);
            g.drawRect(x + (10 * width), y + (1 * height), 4 * width, 4 * height);
            g.drawRect(x + (1 * width), y + (10 * height), 4 * width, 4 * height);
            g.drawRect(x + (10 * width), y + (10 * height), 4 * width, 4 * height);
            g.drawRect(x, y, 15 * width, 15 * height);

            g.drawPolygon(xpoints0, ypoints0, npoints);
            g.drawPolygon(xpoints1, ypoints1, npoints1);
            g.drawPolygon(xpoints2, ypoints2, npoints2);
            g.drawPolygon(xpoints3, ypoints3, npoints3);
        }
    }

    private static class GridButton extends JButton {
        public int x, y;

        public GridButton(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("LUDO Game Board");
            PathController pathController = new PathController("/boardpath.json");
            frame.add(new BoardPanel(pathController));
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class Rule extends JFrame {
    public Rule() {
        setTitle("Game Rules");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("Game rules go here...", SwingConstants.CENTER);
        add(label);
        setVisible(true);
    }
}
