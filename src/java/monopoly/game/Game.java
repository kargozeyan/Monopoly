package monopoly.game;


import monopoly.game.board.Board;
import monopoly.game.board.cell.Cell;
import monopoly.game.card.ChanceStack;
import monopoly.game.card.ChestStack;
import monopoly.ui.scene_controllers.game.GameUI;

public class Game {
    private GameUI ui;

    private Board board = new Board();
    private Dice dice = new Dice();
    private ChanceStack chanceStack = new ChanceStack();
    private ChestStack chestStack = new ChestStack();
    private Players players = new Players();


    public Game(GameUI gui) {
        this.ui = gui;
    }

    public Cell[] getCells() {
        return board.getCells();
    }

    public void addPlayer(Player player) {
        players.addPlayer(player);
    }

    public void start() {}

}
