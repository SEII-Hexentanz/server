package at.aau;

import java.security.SecureRandom;

public class Dice {
    private final SecureRandom ran;
    // as sonarcloud suggests due to security reason use security random instead of ran

    public Dice() {
        ran = new SecureRandom();
    }

    public int useDice() {
        return ran.nextInt(6) + 1;
    }
}
