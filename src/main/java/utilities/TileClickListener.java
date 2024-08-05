package utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileClickListener implements ActionListener {
    private int x, y;

    public TileClickListener(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        // Handle tile click logic, e.g., moving tokens
        System.out.println("Tile clicked at: " + x + ", " + y);
    }
}

