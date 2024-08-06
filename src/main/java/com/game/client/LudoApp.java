package com.game.client;

import com.game.components.board.BoardPanel;
import com.game.gui.WelcomeWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;


@SpringBootApplication
public class LudoApp extends JFrame {

    public LudoApp() {
        setTitle("Ludo Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(916, 975);
        setLocationRelativeTo(null);
        //setResizable(false);
        BoardPanel boardPanel = new BoardPanel(new PathController("src/main/resources/boardpath.json"));
        add(boardPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        System.out.println("java.awt.headless: " + System.getProperty("java.awt.headless"));

        SpringApplication.run(LudoApp.class, args);

        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("No graphical environment detected. GUI will not be started.");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            try {
                // Display the welcome window
                WelcomeWindow welcomeWindow = new WelcomeWindow();
                welcomeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                welcomeWindow.setVisible(true);

                // Wait for the welcome window to be closed before proceeding
                welcomeWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        JFrame jframe = new LudoApp();
                        jframe.setVisible(true);
                    }
                });

            } catch (HeadlessException e) {
                System.err.println("Cannot initialize GUI: " + e.getMessage());
            }
        });
    }
}
