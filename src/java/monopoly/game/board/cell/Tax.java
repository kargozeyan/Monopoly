package monopoly.game.board.cell;

import monopoly.game.Player;

public class Tax extends Cell {
    private final int tax;

    public Tax(String name, int tax) {
        super(name);
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
}
