package monopoly.game.board.cell;

import monopoly.game.Player;

public class Utility extends PricedCell {
    private static final int RENT_COEFFICIENT_FOR_ONE = 4;
    private static final int RENT_COEFFICIENT_FOR_TWO = 10;

    public static Utility ELECTRICITY = new Utility("Electric Company", 150);
    public static Utility WATER = new Utility("Water works", 150);

    private Utility(String name, int price) {
        super(name, price);
    }

    @Override
    public String getData() {
        return String.format("Rent if owned one: %s * last roll number\nRent if owned both: %s * last roll number",
                RENT_COEFFICIENT_FOR_ONE, RENT_COEFFICIENT_FOR_TWO);
    }

    @Override
    public int getRent() {
        int numberOfUtilities = getOwner().getNumberOfUtilities();
        if (numberOfUtilities == 1) {
            return RENT_COEFFICIENT_FOR_ONE * getOwner().lastRoll();
        }
        return RENT_COEFFICIENT_FOR_TWO * getOwner().lastRoll();
    }
}
