package monopoly.game.board.cell;

import monopoly.game.Player;

public abstract class Cell {
    private final String name;

    public Cell(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
