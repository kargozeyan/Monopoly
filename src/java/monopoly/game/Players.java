package monopoly.game;

import java.util.ArrayList;

public class Players {
    private ArrayList<Player> players = new ArrayList<>();

    private int currentTurn = -1;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player next() {
        if (++currentTurn >= players.size())
            currentTurn -= players.size();

        Player next = players.get(currentTurn);
        return next;
    }
}
