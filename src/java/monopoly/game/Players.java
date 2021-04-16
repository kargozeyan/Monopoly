package monopoly.game;

import java.util.ArrayList;

public class Players {
    private ArrayList<Player> players;

    private int currentTurn = -1;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player next() {
        currentTurn++;
        if (currentTurn >= players.size())
            currentTurn = players.size() - currentTurn;
        Player next = players.get(currentTurn);
        // TODO if (next.isSkipping) next();
        return next;
    }
}
