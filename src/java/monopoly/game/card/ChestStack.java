package monopoly.game.card;

public class ChestStack extends CardStack{
    public ChestStack() {
        super(
                new Card[]{
                        new Card("", player -> {}),
                        new Card("", player -> {}),
                        new Card("", player -> {}),
                        new Card("", player -> {}),
                        new Card("", player -> {})
                });
    }
}
