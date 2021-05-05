package monopoly.ui.scene_controllers.game;

import monopoly.game.Player;
import monopoly.util.Function;

public interface GameUI {
    void showDiceAndMove(Function onOver, int[] numbers);
    void movePlayer(Player pLayer);
    void initCurrentPlayer(Player player);
    void updatePlayerBalances();
    void removePlayer(Player player);

    void showMessage(String message);
    boolean askForApprove(String message);

    void announceWinner(Player player);
}
