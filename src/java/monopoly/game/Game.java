package monopoly.game;


import monopoly.game.board.Board;
import monopoly.game.board.cell.Cell;
import monopoly.game.card.ChanceStack;
import monopoly.game.card.ChestStack;
import monopoly.ui.scene_controllers.game.GameGUI;

public class Game {
    private GameGUI gui;

    private Board board = new Board();
    private Dice dice = new Dice();
    private ChanceStack chanceStack = new ChanceStack();
    private ChestStack chestStack = new ChestStack();
    private Players players = new Players();
    // private Bank bank;


    public Game(GameGUI gui) {
        this.gui = gui;
    }

    public Cell[] getCells() {
        return board.getCells();
    }
}
