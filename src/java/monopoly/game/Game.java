package monopoly.game;


import monopoly.game.board.Board;
import monopoly.game.board.cell.CC_Cell;
import monopoly.game.board.cell.Cell;
import monopoly.game.card.Card;
import monopoly.game.card.ChanceStack;
import monopoly.game.card.ChestStack;
import monopoly.ui.scene_controllers.game.GameUI;
import monopoly.util.Function;

public class Game implements IGame {
    private GameUI ui;

    private Board board = new Board();
    private Dice dice = new Dice();
    private ChanceStack chanceStack = new ChanceStack();
    private ChestStack chestStack = new ChestStack();
    private Players players = new Players();

    private Player current;

    public Game(GameUI gui) {
        this.ui = gui;
    }

    public Cell[] getCells() {
        return board.getCells();
    }

    public void addPlayer(Player player) {
        player.initGame(this);
        player.initDice(dice);
        players.addPlayer(player);
    }

    public void start() {
        players.shuffle();

        nextPlayer();
    }


    public void nextPlayer() {
        current = players.next();
        ui.initCurrentPlayer(current);
    }

    public void nextPlayer(Player player) {
        ui.initCurrentPlayer(current);
    }

    @Override
    public void showDiceAndMove(Function onOver, int[] dice) {
        ui.showDiceAndMove(onOver, dice);
    }

    @Override
    public Card takeCard(Cell cell) {
        if (cell == CC_Cell.CHANCE) {
            return chanceStack.pop();
        } else {
            return chestStack.pop();
        }
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void gameLost(Player player) {
        players.removePlayer(player);
        ui.removePlayer(player);
    }

    @Override
    public void updateBalances() {
        ui.updatePlayerBalances();
    }

    @Override
    public void showMessage(String message) {
        ui.showMessage(message);
    }

    @Override
    public boolean askForApprove(String message) {
        return ui.askForApprove(message);
    }

    @Override
    public void movePlayer(Player player) {
        ui.movePlayer(player);
    }
}
