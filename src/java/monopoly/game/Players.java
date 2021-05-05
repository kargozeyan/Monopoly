package monopoly.game;

import java.util.ArrayList;
import java.util.Collections;

public class Players {
    private ArrayList<Player> players = new ArrayList<>();

    private int currentTurn = -1;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);

        if (players.size() == 1) {
            players.get(0).win();
        }
    }

    public Player next() {
        if (currentTurn != -1)
            players.get(currentTurn).resetTurnCounter();

        if (++currentTurn >= players.size())
            currentTurn -= players.size();
        Player next = players.get(currentTurn);
        if (next.isSkipping()) {
            next.skip();
            next();
        }
        return players.get(currentTurn);
    }

    public void shuffle() {
        Collections.shuffle(players);
    }
}
