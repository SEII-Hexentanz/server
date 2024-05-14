package at.aau;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Locale;

import at.aau.payloads.Payload;

public class Dice implements Payload, Serializable {
    private int diceNumber;
    private SecureRandom ran;
    // as sonarcloud suggests due to security reason use security random instead of ran



    public Dice(){
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


    public void setDice(int diceNumber){this.diceNumber=diceNumber;}

    public void setRan(SecureRandom ran) {
        this.ran = ran;
    }




}
