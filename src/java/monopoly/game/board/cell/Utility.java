package monopoly.game.board.cell;

import monopoly.game.Player;

public class Utility extends PricedCell {
    public static String NAME_ELECTRICITY = "Electric Company";
    public static String NAME_WATER = "Water works";
    public static Utility Electricity() {
        return new Utility(NAME_ELECTRICITY, 9);
    }

    public static Utility Water() {
        return new Utility(NAME_WATER, 9);
    }

    private Utility(String name, int price) {
        super(name, price);
    }

    @Override
    public void onLand(Player player) {

    }
}
