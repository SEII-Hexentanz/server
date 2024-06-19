package at.aau.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    private Dice dice;

    @BeforeEach
    public void setup() {
        dice = new Dice();
    }

    @RepeatedTest(10)
    public void diceRollsNumberBetweenOneAndSix() {
        int roll = dice.useDice();
        assertTrue(roll >= 1 && roll <= 6);
    }
}