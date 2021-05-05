package monopoly.game.board.cell;

import monopoly.game.Player;

public class CornerCell extends Cell {
    public static final CornerCell GO = new CornerCell("Go");
    public static final CornerCell JAIL = new CornerCell("Jail");
    public static final CornerCell GO_JAIL = new CornerCell("Go to jail");
    public static final CornerCell FREE_PARKING = new CornerCell("Free parking");

    private CornerCell(String name) {
        super(name);
    }
}
