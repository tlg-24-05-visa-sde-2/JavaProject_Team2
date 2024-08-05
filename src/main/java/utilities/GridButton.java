package utilities;

import javax.swing.*;
import java.awt.*;

public class GridButton extends JButton {
    public int x, y;

    public GridButton(int x, int y) {
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE));
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid lines
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}