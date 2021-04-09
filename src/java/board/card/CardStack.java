package board.card;

import java.util.*;

public class CardStack extends Stack<Integer> {
    private final List<Integer> CARDS = Arrays.asList(1, 2, 3, -1, -2, -3);

    public CardStack() {
        shuffleAndAdd();
    }


    private void shuffleAndAdd() {
        Collections.shuffle(CARDS);
        addAll(CARDS);
    }

    @Override
    public synchronized Integer pop() {
        int card = super.pop();
        if (empty()) {
            shuffleAndAdd();
        }

        return card;
    }
}
