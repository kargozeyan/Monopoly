package monopoly.game.board.cell;

import monopoly.game.Player;

public class Chance extends Cell {

    public Chance(String name) {
        super(name);
    }

    @Override
    public void onLand(Player player) {
        // TODO player.takeChanceCard()
    }
}
