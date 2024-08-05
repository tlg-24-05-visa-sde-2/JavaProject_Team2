package utilities;

import java.awt.*;

public enum TeamColor {

        RED(Color.RED),
        GREEN(Color.GREEN),
        YELLOW(Color.YELLOW),
        BLUE(Color.BLUE);

        private final Color color;

        TeamColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }