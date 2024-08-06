package com.game.components;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;


public class DieTest {
    private Die die;

    @Before
    public void setUp() {
        die = new Die();
    }

    @Test
    public void testRollDice() {
        int lastRoll = die.rollDice();
        assertTrue("Last roll should be between 1 and 6", lastRoll >= 1 && lastRoll <= 6);

        int sixCount = 0;
        for (int roll : die.dieNums) {
            if (roll == 6) {
                sixCount++;
            }
        }
        assertTrue("Six count should be between 0 and 3", sixCount >= 0 && sixCount <= 3);
    }

    @Test
    public void testResetDieNums() {
        die.rollDice(); // Populate dieNums with some rolls
        die.resetDieNums();

        int[] expected = {0, 0, 0};
        assertTrue("dieNums should be reset to all zeros", Arrays.equals(die.dieNums, expected));
    }

    @Test
    public void testToString() {
        die.rollDice();
        String result = die.toString();
        assertTrue("toString should contain 'Die: last roll outcome was: '", result.contains("Die: last roll outcome was: "));
        assertTrue("toString should contain 'die rolls: '", result.contains("die rolls: "));
    }
}

