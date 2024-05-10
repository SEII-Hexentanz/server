package at.aau;

import java.io.Serializable;
import java.security.SecureRandom;

import at.aau.payloads.Payload;

public class Dice implements Payload, Serializable {
    private int diceNumber;

    public Dice(int diceNumber){
        this.diceNumber = diceNumber;
    }
    public int getDiceNumber() {
        return diceNumber;
    }

}
