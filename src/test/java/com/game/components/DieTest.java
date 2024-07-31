package test.java.com.game.components;

import main.java.com.game.components.Die;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DieTest {
    Die die = new Die();

    @Test
    public void testRollDice(){
        int result = die.rollDice();
        System.out.println(die);
        Assert.assertTrue("Roll result should be between 1 and 6", result >= 1 && result <= 6);
    }
}