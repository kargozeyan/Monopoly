package monopoly.game.board.cell;

import monopoly.game.Player;

public class Property extends PricedCell {
    private String name;
    private ColorGroup colorGroup;
    final int rents;
    int lvl = 0;

    public Property(String name, int price, ColorGroup colorGroup, int[] rents) {
        super(name, price);
        this.colorGroup = colorGroup;
    }

    public void setColorGroup(ColorGroup colorGroup) {
        this.colorGroup = colorGroup;
    }

    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    public int getLvl() {
        return lvl;
    }


    public void upgrade() {
        lvl += 1;
    }

    public int getrents() {
        return lvl;
    }
}
