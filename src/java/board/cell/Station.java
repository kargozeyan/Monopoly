package board.cell;

import java.Player;

public class Station extends PricedCell {
    public Station(String name, int price) {
        super(name, price);
    }

    @Override
    public void onLand(Player player) {

    }
}
