package com.game.components;

import com.game.components.player.Player;
import com.game.utilities.TeamColor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Arrays;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {

    private Token token;
    private Player currentPlayer;
    private Player opponentPlayer;

    @Before
    public void setUp() {
        currentPlayer = new Player("CurrentPlayer", TeamColor.RED);
        opponentPlayer = new Player("OpponentPlayer", TeamColor.BLUE);

        // Add initial positions for tokens
        currentPlayer.token.addToken("token1", 2, 3);
        currentPlayer.token.addToken("token2", 0, 0);
        opponentPlayer.token.addToken("token1", 10, 10);
        opponentPlayer.token.addToken("token2", 20, 20);
    }

    @Ignore
    @Test // not really sure how to test this part without interacting with the board
    public void testMoveForward() {
        currentPlayer.token.moveForward("token1", 5, currentPlayer, opponentPlayer);
        Map<String, Integer> position = token.getTokenPosition("token1");
        assertEquals(5, position.get("y"));
    }

    @Test
    public void testGoToJail() {
        opponentPlayer.token.goToJail("token1");
        Map<String, Integer> position = opponentPlayer.token.getTokenPosition("token1");
        assertEquals(0, position.get("x")); // should work as x and y get reset to zero zero
        assertEquals(0, position.get("y"));
    }

    @Test
    public void testChangeDirection() {
        // move towards safe zone
        currentPlayer.token.changeDirection("token1", 2, currentPlayer.token.getTokenPosition("token1"));
        Map<String, Integer> position = currentPlayer.token.getTokenPosition("token1");
        assertEquals(4, position.get("y"));  // Adjust based on actual changeDirection logic in change direction method
    }

    @Test
    public void testGoHome_whenTokenPassAsArgument_shouldIncrementTokenInHomeByOne() {
        currentPlayer.token.goHome("token1");
        assertEquals(1, currentPlayer.token.tokenInHome, "Token in home should be incremented to 1");

        currentPlayer.token.goHome("token1");
        assertEquals(2, currentPlayer.token.tokenInHome, "Token in home should be incremented to 2");
    }
}
