package monopoly.game;

import monopoly.game.board.Board;
import monopoly.game.board.cell.Cell;
import monopoly.game.card.Card;
import monopoly.util.Function;

import java.util.function.Consumer;

interface IGame {
    void showDiceAndMove(Function onOver, int[] dice);

    Card takeCard(Cell cell);

    Board getBoard();

    void gameLost(Player player);

    void updateBalances();

    void showMessage(String message);
    boolean askForApprove(String message);

    void movePlayer(Player player);
}
