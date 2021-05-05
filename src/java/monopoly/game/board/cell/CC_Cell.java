package monopoly.game.board.cell;

import monopoly.game.Player;

public class CC_Cell extends Cell {
    public static CC_Cell CHANCE = new CC_Cell("Chance");

    public static CC_Cell CHEST = new CC_Cell("Chest");


    private CC_Cell(String name) {
        super(name);
    }

    public String getDesc() {
        return String.format("Test your luck by opening %s cards", getName());
    }
}
