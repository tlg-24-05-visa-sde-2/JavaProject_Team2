package com.game.client;

import com.game.gui.SwingGameBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class LudoApp {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        System.out.println("java.awt.headless: " + System.getProperty("java.awt.headless"));

        SpringApplication.run(LudoApp.class, args);
        //Application.launch(LudoFx.class, args);

        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("No graphical environment detected. GUI will not be started.");
            return;
        }
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Game Board");
                SwingGameBoard gameBoard = new SwingGameBoard();
                frame.add(gameBoard);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (HeadlessException e) {
                System.err.println("Cannot initialize GUI: " + e.getMessage());
            }
        });
    }
}