package com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame implements ActionListener {
    JButton playBtn;
    JButton rulesBtn;

    WelcomeWindow() {
        rulesBtn = new JButton("Rules");
        rulesBtn.setBounds(400, 320, 100, 50);
        rulesBtn.addActionListener(this);


        playBtn = new JButton("Play");      //play button
        playBtn.setBounds(400, 320, 100, 50);
        playBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(playBtn);
        buttonPanel.add(rulesBtn);
        buttonPanel.setBackground(new Color(100, 197, 255));

        //JLabel = a GUI area for a string of text, an image or both
        JLabel label = new JLabel("<html>" +
                "Welcome to Ludo,<br>" +
                "Where the thrill's in the race,<br>" +
                "Roll the dice & aim for first place!!</html");
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        label.setBounds(50, 50, 400, 295);

        ImageIcon welcome = new ImageIcon("logo/ludothumbnail.jpg");
        label.setIcon(welcome);

        this.setLayout(new BorderLayout());
        this.setTitle("Ludo");   //sets title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits application
//        this.setResizable(false);      //prevent the window from being resized
        this.setSize(450, 450); //sets the x-dimension, and y-dimension of frame
        this.setVisible(true);              // makes frame visible
        this.add(label, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);


        ImageIcon image = new ImageIcon("logo/ludologo.png");    //creates an Image icon
        this.setIconImage(image.getImage());   //changes icon
        this.getContentPane().setBackground(new Color(100, 197, 255));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            System.out.println("play button pressed");
        } else if (e.getSource() == rulesBtn) {
            System.out.println("rules button pressed");
        }
    }
}