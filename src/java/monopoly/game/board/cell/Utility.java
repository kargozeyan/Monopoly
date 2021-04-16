package monopoly.game.board.cell;

import monopoly.game.Player;

public class Utility extends PricedCell {
    public Utility(String name, int price) {
        super(name, price);
    }

    @Override
    public void onLand(Player player) {

    }
}
