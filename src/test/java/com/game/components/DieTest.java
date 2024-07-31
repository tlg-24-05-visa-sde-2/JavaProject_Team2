package com.game.components;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DieTest {
    Die die = new Die();

    @Test
    public void testRollDice(){
        int result = die.rollDice();
        System.out.println(die);
        Assert.assertTrue(result >= 1 && result <= 6, "Roll result should be between 1 and 6");
    }
}