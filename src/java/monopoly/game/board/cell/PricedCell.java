package monopoly.game.board.cell;

import monopoly.game.Player;

abstract public class PricedCell extends Cell {
    private int price;
    private Player owner;

    public PricedCell(String name, int price) {
        super(name);
        this.price = price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public abstract String getData();
    public abstract int getRent();
}
