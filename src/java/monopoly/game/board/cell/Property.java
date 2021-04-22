package monopoly.game.board.cell;

import monopoly.game.Player;

public class Property extends PricedCell {
    private String name;
    private ColorGroup colorGroup;
    int lvl = 0;

    public Property(String name, int price, ColorGroup colorGroup) {
        super(name, price);
        this.colorGroup = colorGroup;
    }

    @Override
    public void onLand(Player player) {
        // TODO owner.takeMoney()
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
}
