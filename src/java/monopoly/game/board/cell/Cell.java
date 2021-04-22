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

    /**
     * this method is invoked when a player lands on a cell
     *
     * @param player the player that landed on cell
     */
    public abstract void onLand(Player player);
}
