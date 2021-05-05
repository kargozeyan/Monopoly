package monopoly.game;

import java.util.Random;

public class Dice {
    private final int SIDE_NUMBER = 6;
    private final Random random = new Random();
    private int[] lastRoll = {0, 0};

    public int[] roll() {
        int first = random.nextInt(SIDE_NUMBER) + 1;
        int second = random.nextInt(SIDE_NUMBER) + 1;
        lastRoll = new int[]{first, second};
        return getLastRoll();
    }

    public int[] getLastRoll() {
        return lastRoll;
    }
}
