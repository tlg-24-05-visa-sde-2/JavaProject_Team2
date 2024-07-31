import org.w3c.dom.ls.LSOutput;

public class Die {
    int diceNumber;

    int rollDice(){
        return diceNumber = (int)(Math.random()*6)+1;
    }

    @Override
    public String toString(){
        return "Die: last roll outcome was: " + diceNumber;
    }
}