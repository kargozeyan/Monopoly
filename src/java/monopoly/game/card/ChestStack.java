package monopoly.game.card;

import monopoly.game.Player;

public class ChestStack extends CardStack {
    public ChestStack() {
        super(
                new Card[]{
                        new Card("Doctor's fee. 100$", player -> {
                            player.payMoney(100);
                        }),
                        new Card("Pay school tax of 150$", player -> {
                            player.payMoney(150);
                        }),
                        new Card("Get out of Jail free", Player::getJailFreeCard),
                        new Card("Get out of Jail free", Player::getJailFreeCard),
                        new Card("Go to jail", Player::goToJail),
                        new Card("You inherit 100$", player -> {
                            player.receiveMoney(100);
                        }),
                        new Card("From sale of stock, You get 50$", player -> {
                            player.receiveMoney(50);
                        })
                });
    }
}
