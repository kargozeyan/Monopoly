package monopoly.game.board.cell;

import monopoly.game.Player;

public class Property extends PricedCell {
    String name;
    ColorGroup colorGroup;
    int lvl = 0;

    public Property(String name, int price, ColorGroup colorGroup) {
        super(name, price);
        this.colorGroup = colorGroup;
    }

    @Override
    public void onLand(Player player) {
        // TODO owner.takeMoney()
    }

    public void upgrade() {
        lvl += 1;
    }
}
