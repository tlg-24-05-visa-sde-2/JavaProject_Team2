package com.game.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingGameBoard extends JPanel {

    public static final int TILE_SIZE = 60;  // Tile size for each square
    public static final int BOARD_SIZE = 15; // Number of tiles in one row or column
    private BufferedImage backgroundImage;

    public SwingGameBoard() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/board.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(900, 900));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / TILE_SIZE;
                int y = e.getY() / TILE_SIZE;
                handleGridClick(x, y);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 900, 900, this);
        }

        // Draw the grid overlay
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i <= BOARD_SIZE; i++) {
            g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, TILE_SIZE * BOARD_SIZE);
            g.drawLine(0, i * TILE_SIZE, TILE_SIZE * BOARD_SIZE, i * TILE_SIZE);
        }
    }

    private void handleGridClick(int x, int y) {
        // Handle the grid click at (x, y)
        System.out.println("Grid clicked at: (" + x + ", " + y + ")");
        //
    }
}