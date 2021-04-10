package board.card;

import java.util.*;

public class CardStack extends Stack<Card> {
    private final List<Card> cards;

    public CardStack(Card[] cards) {
        this.cards = Arrays.asList(cards);
        shuffleAndAdd();
    }

    private void shuffleAndAdd() {
        Collections.shuffle(cards);
        addAll(cards);
    }

    @Override
    public synchronized Card pop() {
        Card card = super.pop();
        if (empty()) {
            shuffleAndAdd();
        }

        return card;
    }
}
