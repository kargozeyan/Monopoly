package monopoly.game.card;

import monopoly.game.Player;

public class ChanceStack extends CardStack {
    public ChanceStack() {
        super(
                new Card[]{
                        new Card("Go to jail", Player::goToJail),
                        new Card("Bank pays you 50$", player -> {
                            player.receiveMoney(50);
                        }),
                        new Card("Your building and loan matures. Collect 150$", player -> {
                            player.receiveMoney(150);
                        })
                });
    }
}
