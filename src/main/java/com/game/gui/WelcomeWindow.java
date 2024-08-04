package com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main JFrame Class
public class WelcomeWindow extends JFrame implements ActionListener {

    private JButton playBtn;
    private JButton rulesBtn;
    private final JPanel contentPanel; // Panel that has Welcome, Form, and Rules

    // Prevents player from selecting the same team color as the opponent
    private final String[] colors = {"Red", "Blue", "Green", "Yellow"};
    private final JComboBox<String> colorBox1 = new JComboBox<>(colors);
    private final JComboBox<String> colorBox2 = new JComboBox<>(colors);

    // Constructor to initialize the frame
    WelcomeWindow() {
        this.setTitle("Ludo"); // Sets title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits application
        this.setResizable(false); // Prevent the window from being resized
        this.setSize(450, 450); // Sets the x-dimension, and y-dimension of frame
        this.setVisible(true); // Makes frame visible

        // ContentPanel for all Views
        contentPanel = new JPanel(new CardLayout()); // Card layout will let me manage different views

        contentPanel.add(new WelcomePanel(), "Welcome"); // Welcome Panel
        contentPanel.add(new FormPanel(), "Form"); // Form Panel for player info
        contentPanel.add(new RulesPanel(), "Rules"); // Rules panel for rules

        this.add(contentPanel, BorderLayout.CENTER); // Adds content panel to frame
        contentPanel.setBackground(new Color(100, 197, 255)); // Background color

        ImageIcon image = new ImageIcon("logo/ludologo.png"); // Creates an Image icon
        this.setIconImage(image.getImage()); // Changes icon
        this.getContentPane().setBackground(new Color(100, 197, 255)); // Background color
    }

    // Switch between panels (action/method)
    private void showPanel(String panelName) {
        CardLayout card = (CardLayout) contentPanel.getLayout();
        card.show(contentPanel, panelName);
    }

    @Override // Action listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            showPanel("Form");
        } else if (e.getSource() == rulesBtn) {
            showPanel("Rules");
        }
    }

    // Inner class for Welcome Panel
    private class WelcomePanel extends JPanel {
        WelcomePanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(100, 197, 255)); // Background color

            JLabel label = new JLabel("<html>" +
                    "Welcome to Ludo,<br>" +
                    "Where the thrill's in the race,<br>" +
                    "Roll the dice & aim for first place!!</html>");
            label.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

            ImageIcon welcome = new ImageIcon("logo/ludothumbnail.jpg");
            label.setIcon(welcome);

            rulesBtn = new JButton("Rules"); // Rules button
            rulesBtn.addActionListener(WelcomeWindow.this);

            playBtn = new JButton("Play"); // Play button
            playBtn.addActionListener(WelcomeWindow.this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(playBtn);
            buttonPanel.add(rulesBtn);
            buttonPanel.setBackground(new Color(100, 197, 255)); // Background color

            add(label, BorderLayout.CENTER); // Add label to the center of the panel
            add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom
        }
    }

    // Player Form inner class
    private class FormPanel extends JPanel {
        FormPanel() {
            setLayout(new GridLayout(3, 1)); // Adjusted layout to have 3 rows
            setBackground(new Color(100, 197, 255)); // Set background color

            // Player 1
            JPanel p1 = new JPanel();
            p1.setBackground(new Color(100, 197, 255)); // Background color for player 1 panel
            p1.add(new JLabel("Player 1, Enter Your Name:"));
            JTextField p1Name = new JTextField(25);
            p1.add(p1Name);
            p1.add(new JLabel("Choose your team color"));
            p1.add(colorBox1);

            // Player 2
            JPanel p2 = new JPanel();
            p2.setBackground(new Color(100, 197, 255)); // Background color for player 2 panel
            p2.add(new JLabel("Player 2, Enter Your Name:"));
            JTextField p2Name = new JTextField(25);
            p2.add(p2Name);
            p2.add(new JLabel("Choose your team color"));
            p2.add(colorBox2);

            // Lets Play
            JPanel confirmPanel = new JPanel();
            confirmPanel.setBackground(new Color(100, 197, 255)); // Background color for confirm panel
            JButton confirmBtn = new JButton("Let's Play");
            confirmBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (p1Name.getText().isEmpty() || p2Name.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter your name to play", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (colorBox1.getSelectedItem().equals(colorBox2.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "Players cannot choose the same color", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            confirmPanel.add(confirmBtn);

            add(p1);
            add(p2);
            add(confirmPanel);
        }
    }

    // Inner class for Rules Panel
    private class RulesPanel extends JPanel {
        RulesPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(100, 197, 255)); // Background color

            JLabel rulesLabel = new JLabel("<html>" +
                    "<h2>Game Rules</h2>" +
                    "<ol>" +
                    "<li>(Optional) Each player rolls a die to determine who goes first.</li>" +
                    "<li>Players take turns rolling the die and moving their piece the number of squares corresponding to their roll.</li>" +
                    "<li>Players continue to take turns rolling and moving until one player gets both pieces to the safe zone.</li>" +
                    "<li>If Player 2 lands on the same square as Player 1's piece, Player 1 must return their piece to the start. Similarly, if Player 1 lands on the same square as Player 2's piece, Player 2 must return their piece to start.</li>" +
                    "<li>The game is over when one player successfully gets both of their pieces in the safe zone. The player who achieves this first wins the game.</li>" +
                    "</ol>" +
                    "</html>", SwingConstants.CENTER);
            rulesLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            rulesLabel.setForeground(Color.BLACK); // Ensure text color is visible against the background

            JButton backBtn = new JButton("Back to Welcome");
            backBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPanel("Welcome");
                }
            });

            add(rulesLabel, BorderLayout.CENTER); // Add the rules label to the center of the panel
            add(backBtn, BorderLayout.SOUTH); // Add the back button to the bottom
        }
    }
}
