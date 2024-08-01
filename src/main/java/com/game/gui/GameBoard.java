package com.game.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameBoard extends Pane {

    public static final int TILE_SIZE = 30;  // Tile size for each square
    public static final int BOARD_SIZE = 15; // Number of tiles in one row or column

    public GameBoard() {
        // Set the background image
        BackgroundImage bgImage = new BackgroundImage(
                new Image("file:src/main/resources/board.jpg", TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        this.setBackground(new Background(bgImage));

        // Overlay the grid
        drawGrid();
    }

    private void drawGrid() {
        // Example of drawing a grid on top of the background image
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                StackPane tile = new StackPane();
                //tile.setStyle("-fx-border-color: black; -fx-background-color: rgba(255, 255, 255, 0.1);");
                tile.setPrefSize(TILE_SIZE, TILE_SIZE);
                tile.setLayoutX(i * TILE_SIZE);
                tile.setLayoutY(j * TILE_SIZE);
                this.getChildren().add(tile);
            }
        }
    }
}

