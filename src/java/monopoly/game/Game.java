package monopoly.game;


import monopoly.game.board.Board;
import monopoly.game.card.ChanceStack;
import monopoly.game.card.ChestStack;

public class Game {
    private Board board = new Board();
    private Dice dice = new Dice();
    private ChanceStack chanceStack = new ChanceStack();
    private ChestStack chestStack = new ChestStack();
    private Players players = new Players();
    // private Bank bank;
}
