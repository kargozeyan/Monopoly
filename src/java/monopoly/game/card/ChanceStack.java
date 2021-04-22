package monopoly.game.card;

public class ChanceStack extends CardStack {
    public ChanceStack() {
        super(
                new Card[]{
                        new Card("", player -> {
                        }),
                        new Card("", player -> {
                        }),
                        new Card("", player -> {
                        }),
                        new Card("", player -> {
                        }),
                        new Card("", player -> {
                        })
                });
    }
}
