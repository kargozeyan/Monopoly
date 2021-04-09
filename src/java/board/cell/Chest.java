package board.cell;

import java.Player;

public class Chest extends Cell {

    public Chest(String name) {
        super(name);
    }

    @Override
    public void onLand(Player player) {
        // TODO player.takeChestCard()
    }
}
