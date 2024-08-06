package com.game.components;

import com.game.components.player.Player;
import com.game.utilities.TeamColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class wPlayerTest {

    @Test
    public void testPlayerConstructorWithValidParameters() { // Verify the player's name and color
        Player player = new Player("El Jefe", TeamColor.RED);
        assertEquals("El Jefe", player.getName(), "Player name should be correctly initialized");
        assertEquals(TeamColor.RED, player.getPlayerColor(), "Player color should be correctly initialized");
    }

    @Test
    public void testPlayerConstructorWithEmptyStringName_shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player("", TeamColor.RED);
        });
        assertEquals("Player name cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testPlayerConstructorWithNullName_shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(null, TeamColor.RED);
        });
        assertEquals("Player name cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testPlayerToString() {
        String playerName = "El Jefe";
        TeamColor teamColor = TeamColor.RED;
        Player player = new Player(playerName, teamColor);

        assertEquals("Player: name is: " + playerName + "\nplayerColor is: " + teamColor, player.toString());
    }

    @Test
    public void testPlayerWin() { // this should call the GUI to show some window for the viewer I believe
        Player player = new Player("El Jefe", TeamColor.RED);
        player.win();
    }
}