package board.cell;

import java.Player;

public class Tax extends Cell {
    private final int tax;

    public Tax(String name, int tax) {
        super(name);
        this.tax = tax;
    }

    @Override
    public void onLand(Player player) {
        // TODO player.payMoney(tax)
    }

    public int getTax() {
        return tax;
    }
}
