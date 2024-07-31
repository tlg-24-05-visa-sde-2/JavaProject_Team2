package com.game.controller;

public class GameController {
    public static void execute() {
        GuiController.loadGame();
        GuiController.startGame();
        GuiController.saveGame();
        GuiController.replayGame();
        GuiController.endGame();
    }
}