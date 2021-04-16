package monopoly.game.board.cell;


import monopoly.game.Player;

public class Chest extends Cell {

    public Chest(String name) {
        super(name);
    }

    @Override
    public void onLand(Player player) {
        // TODO player.takeChestCard()
    }
}
