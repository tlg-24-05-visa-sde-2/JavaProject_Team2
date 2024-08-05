package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pawn extends JComponent {
    private int x, y;
    private int current;
    private int height, width;

    public Pawn(int h, int w) {
        this.height = h;
        this.width = w;
        this.current = -1;
        this.x = -1;
        this.y = -1;

        setOpaque(false); // Make sure the background is transparent
        setPreferredSize(new Dimension(width, height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        // Add logic to handle pawn click if needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Drawing the pawn
        g2d.setColor(Color.RED);
        g2d.fillOval(5, 5, width - 10, height - 10);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(5, 5, width - 10, height - 10);

        g2d.dispose();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setLocation(x, y);
        repaint();
    }
}