package com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RuleWindow extends JFrame {
    private JButton playBtn;

    public RuleWindow() {
        this.setTitle("Rules");
        this.setSize(450, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        JLabel rulesLabel = new JLabel("<html>" +
                "<h2>Game Rules</h2>" +
                "<ol>" +
                "<li>(Optional) Each players rolls a die to determine who goes first. </li>" +
                "<li>Players take turn rolling the die and moving their piece the number of squares corresponding to their roll</li>" +
                "<li>Players continue to take turns rolling and moving until one player gets both pieces to the safe zone.</li>" +
                "<li>If Player 2 lands on the same square as Player 1's piece, Player 1 must return their piece to the start. Similarly, if Player 1 lands on the same square as Player 2's piece, Player 2 must return their piece to start.</li>" +
                "<li>The game is over when one player successfully gets both of their pieces in the safe zone. Player who achieves this first wins the game.</li>" +
                "<ol>" +
                "</html>", SwingConstants.CENTER);
        rulesLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        this.add(rulesLabel, BorderLayout.CENTER);
        this.getContentPane().setBackground(new Color(100, 197, 255));
        this.setVisible(true);

        playBtn = new JButton("Let's Play");
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(rulesLabel, BorderLayout.CENTER);
        panel.add(playBtn, BorderLayout.SOUTH);

        this.add(panel);
    }
}