package com.game.components;

import com.game.components.player.Player;
import com.game.utilities.TeamColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testng.annotations.Ignore;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {

    private Token token;
    private Player currentPlayer;
    private Player opponentPlayer;

    @BeforeEach
    public void setUp() {
        currentPlayer = new Player("CurrentPlayer", TeamColor.RED);
        opponentPlayer = new Player("OpponentPlayer", TeamColor.BLUE);

        // Add initial positions for tokens
        currentPlayer.token.addToken("token1", 0, 0);
        currentPlayer.token.addToken("token2", 0, 0);
        opponentPlayer.token.addToken("token1", 10, 10);
        opponentPlayer.token.addToken("token2", 20, 20);
    }

    @Disabled
    @Test
    public void testMoveForward() {
        currentPlayer.token.moveForward("token1", 5, currentPlayer, opponentPlayer);
        Map<String, Integer> position = token.getTokenPosition("token1");
        assertEquals(5, position.get("y"));
    }

    @Test
    public void testGoToJail() {
        currentPlayer.token.goToJail("token1");
        Map<String, Integer> position = currentPlayer.token.getTokenPosition("token1");
        assertEquals(0, position.get("x"));
        assertEquals(0, position.get("y"));
    }

    @Test
    public void testChangeDirection() {
        // move towards safe zone
        currentPlayer.token.changeDirection("token1", 2, currentPlayer.token.getTokenPosition("token1"));
        Map<String, Integer> position = currentPlayer.token.getTokenPosition("token1");
        assertEquals(1, position.get("y"));  // Adjust based on actual changeDirection logic
    }

    @Test
    public void testGoHome() {
        currentPlayer.token.goHome("token1");

    }
}
