package monopoly.game.board.cell;

import monopoly.game.Player;

public class Property extends PricedCell {
    private String name;
    private ColorGroup colorGroup;
    final int[] rents;
    private int lvl = 0;

    public Property(String name, int price, ColorGroup colorGroup, int[] rents) {
        super(name, price);
        this.colorGroup = colorGroup;
        this.rents = rents;
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

    @Override
    public int getRent() {
        if (lvl == 0) {
            return getOwner().hasAllColorGroupProperties(colorGroup) ? rents[lvl] * 2 : rents[lvl];
        }

        return rents[lvl];
    }

    @Override
    public String getData() {
        StringBuilder format = new StringBuilder("Owner: %s\nLevel: %d\nRent: %d");
        for (int i = 1; i < rents.length; i++)
            format.append("\nRent of LVL").append(i + 1).append(": ").append(rents[i]);

        return String.format(format.toString(),
                hasOwner() ? getOwner().getName() : "None",
                lvl,
                rents[0]);
    }
}
