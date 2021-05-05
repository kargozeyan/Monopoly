package monopoly.game.card;

import monopoly.game.Player;

public class Card {
    private final String message;
    private final CardAction cardAction;

    public Card(String message, CardAction cardAction) {
        this.message = message;
        this.cardAction = cardAction;
    }

    public void open(Player player) {
        cardAction.act(player);
    }

    public String getMessage() {
        return message;
    }
}
