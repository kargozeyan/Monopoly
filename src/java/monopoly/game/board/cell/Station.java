package monopoly.game.board.cell;

import monopoly.game.Player;

public class Station extends PricedCell {
    private static final int RENT = 25;

    public Station(String name, int price) {
        super(name, price);
    }

    @Override
    public String getData() {
        StringBuilder data = new StringBuilder();
        data.append("Owner: ").append(!hasOwner() ? "None" : getOwner().getName());
        data.append("\nRent if\n");
        for (int i = 0; i < 4; i++) {
            data.append(i + 1).append(" Stations owned:").append((int)(RENT * Math.pow(2, i))).append('\n');
        }
        return data.toString();
    }

    @Override
    public int getRent() {
        return (int) (RENT * Math.pow(2, getOwner().getNumberOfStations() - 1));
    }
}
