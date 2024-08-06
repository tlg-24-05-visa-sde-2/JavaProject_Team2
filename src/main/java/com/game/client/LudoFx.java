package com.game.client;

import com.game.gui.GameBoard;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class LudoFx extends Application {
    public LudoFx(){}

    @Override
    public void start(Stage primaryStage) {

//        GameController.execute();
//        System.out.println("hello world");

        // Create an instance of GameBoard
        GameBoard gameBoard = new GameBoard();

        // Create and set the Scene
        Scene scene = new Scene(gameBoard, GameBoard.TILE_SIZE * GameBoard.BOARD_SIZE, GameBoard.TILE_SIZE * GameBoard.BOARD_SIZE);
        // Adjust size based on your design

        primaryStage.setTitle("LUDO Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
            launch(args);
    }
}
