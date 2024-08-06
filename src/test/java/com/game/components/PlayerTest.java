package com.game.components.player;

import com.game.utilities.TeamColor;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testPlayerConstructorWithValidParameters() { // Verify the player's name and color
        Player player = new Player("El Jefe", TeamColor.RED);
        assertEquals("El Jefe", player.getName());
        assertEquals(TeamColor.RED, player.getPlayerColor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerConstructorWithEmptyStringName_shouldThrowIllegalArgumentException() {
        try{
            new Player("", TeamColor.RED);
        }
        catch(IllegalArgumentException e){
            assertEquals("Player name cannot be null or empty", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerConstructorWithNullName_shouldThrowIllegalArgumentException() {
        try{
            new Player(null, TeamColor.RED);
        }
        catch(IllegalArgumentException e){
            assertEquals("Player name cannot be null or empty", e.getMessage());
            throw e;
        }
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
