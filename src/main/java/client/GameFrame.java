package client;


import utilities.BoardPanel;
import utilities.PathController;

import javax.swing.*;


public class GameFrame extends JFrame {
    public GameFrame() {
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
        SwingUtilities.invokeLater(GameFrame::new);
    }
}