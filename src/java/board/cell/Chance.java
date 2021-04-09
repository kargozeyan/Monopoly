package board.cell;

import java.Player;

public class Chance extends Cell {

    public Chance(String name) {
        super(name);
    }

    @Override
    public void onLand(Player player) {
        // TODO player.takeChanceCard()
    }
}
