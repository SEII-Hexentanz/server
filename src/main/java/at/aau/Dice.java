package at.aau;

import java.security.SecureRandom;

public class Dice {
    private int diceNumber;
    private SecureRandom ran;
    // as sonarcloud suggests due to security reason use security random instead of ran

    public Dice() {
        this.diceNumber = diceNumber;
        ran = new SecureRandom();
    }

    public int getDiceNumber() {
        return diceNumber;

    }

    public int useDice() {
        return diceNumber = ran.nextInt(6) + 1;
    }

    //is also sum of dice
    public int getDice() {
        return diceNumber;
    }


    public void setDice(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    public void setRan(SecureRandom ran) {
        this.ran = ran;
    }

}
