package monopoly.game.board.cell;

import monopoly.game.Player;

public class CC_Cell extends Cell {
    public static String NAME_CHANCE="Chance";
    public static String NAME_CHEST="Community Chest";
    public static CC_Cell Chance() {
        return new CC_Cell(NAME_CHANCE);
    }

    public static CC_Cell Chest() {
        return new CC_Cell(NAME_CHEST);
    }

    private CC_Cell(String name) {
        super(name);
    }

    @Override
    public void onLand(Player player) {
        // TODO player.takeChanceCard()
    }
}
