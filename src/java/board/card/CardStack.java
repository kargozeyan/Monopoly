package board.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class CardStack extends Stack<Card> {
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
