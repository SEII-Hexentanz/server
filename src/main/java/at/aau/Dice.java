package at.aau;

import java.security.SecureRandom;

// fixme move to game logic package
public class Dice {
    private final SecureRandom ran;
    // as sonarcloud suggests due to security reason use security random instead of ran
    // fixme you dont need to use a secure random for a dice roll

    public Dice() {
        ran = new SecureRandom();
    }

    public int useDice() {
        return ran.nextInt(6) + 1;
    }
}
