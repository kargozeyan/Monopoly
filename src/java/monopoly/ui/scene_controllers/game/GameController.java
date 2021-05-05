package monopoly.ui.scene_controllers.game;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import monopoly.game.Game;
import monopoly.game.Player;
import monopoly.game.board.cell.*;
import monopoly.ui.custom_node.*;
import monopoly.ui.scene_controllers.BaseController;
import monopoly.ui.scene_controllers.DataReceiver;
import monopoly.ui.utils.FXUtils;
import monopoly.util.Function;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable, DataReceiver, GameUI {
    @FXML
    Pane root;

    @FXML
    Button rollBtn;

    @FXML
    Button sellBtn;

    @FXML
    Button buyBtn;

    @FXML
    Button tradeBtn;

    @FXML
    Button doneBtn;

    @FXML
    VBox buttons;

    @FXML
    Text info;

    @FXML
    Button upgradeBtn;

    @FXML
    Button payBtn;

    @FXML
    Text playersBalance;

    // Parts of board
    private final HBox left = new HBox();
    private final HBox right = new HBox();
    private final HBox top = new HBox();
    private final HBox bottom = new HBox();

    // dice
    private Die die1, die2;
    // logo
    private ImageView logo;

    // markers
    private Map<Player, PlayerMarker> markers = new HashMap<>();

    private final Game game = new Game(this);
    private int cellCount; // the number of cells in one hbox, excluding corners
    private double boxLength;

    private Player current;

    Player p1 = new Player("A1");
    Player p2 = new Player("A2");
    Player p3 = new Player("A3");

    private DetailedNode detailedNode = new DetailedNode();

    private DialogHelper dialogHelper = new DialogHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adding CSS and font
        root.getStylesheets().add(FXUtils.getCSS("board.css"));
        root.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap");

        // initializing board
        initBoard();
        // rotating board parts accordingly
        rotateHBoxes();

        // init logo and dice
        initLogo();
        initDice();

        markers.put(p1, new PlayerMarker(PlayerMarker.Color.BLUE));
        markers.put(p2, new PlayerMarker(PlayerMarker.Color.GREEN));
        markers.put(p3, new PlayerMarker(PlayerMarker.Color.YELLOW));

        // init starting button
        initStartBtn();

        // place player buttons
        placeButtons();

        // init Detail Node
        initDetailNode();

        // init players balance
        initPlayersBalance();

        Button button = new Button("T");
        button.setLayoutX(300);
        button.setLayoutY(300);

        button.setOnAction(actionEvent -> {
            p1.goToJail();
        });

        root.getChildren().add(button);
    }

    private void initPlayersBalance() {
        playersBalance.setLayoutX(boxLength + 196);
        playersBalance.setLayoutY(196);
        updatePlayerBalances();
    }

    @Override
    public void updatePlayerBalances() {
        StringBuilder balances = new StringBuilder();
        markers.keySet().forEach(player -> {
            balances.append(String.format("%s: %d\n", player.getName(), player.getBalance()));
        });
        playersBalance.setText(balances.toString());
    }

    private void initStartBtn() {
        Button button = new Button("Start");
        button.setPrefWidth(BaseNode.WIDTH);
        button.setPrefHeight(BaseNode.WIDTH / 4);
        button.getStyleClass().add("btn");
        button.setLayoutX(BaseNode.HEIGHT + cellCount / 2 * BaseNode.WIDTH);
        button.setLayoutY(logo.getLayoutY() + 164);
        button.setOnAction(actionEvent -> {
            addPlayers();
            button.setVisible(false);
            buttons.setVisible(true);
            playersBalance.setVisible(true);
        });
        root.getChildren().add(button);
    }

    private void initDetailNode() {
        detailedNode.setLayoutX(boxLength - DetailedNode.WIDTH - 32);
        detailedNode.setLayoutY(boxLength - DetailedNode.HEIGHT - 32);
        detailedNode.setData(game.getCells()[1]);
        root.getChildren().add(detailedNode);
    }

    private void addPlayers() {
        int i = 0;
        double[] startPoint = getCoordinatesByIndex(0);

        for (Map.Entry<Player, PlayerMarker> entry : markers.entrySet()) {
            entry.getValue().setPosition(i, startPoint[0], startPoint[1]);
            root.getChildren().add(entry.getValue());
            i++;
            game.addPlayer(entry.getKey());
        }

        game.start();

    }

    private void initBoard() {
        Cell[] cells = game.getCells();
        cellCount = cells.length / 4 - 1;
        boxLength = cellCount * BaseNode.WIDTH + BaseNode.HEIGHT;
        int bottomStart = 0;
        int leftStart = cells.length / 4;
        int topStart = 2 * cells.length / 4;
        int rightStart = 3 * cells.length / 4;

        for (int i = 0; i < cells.length / 4; i++) {
            Cell bottomCell = cells[bottomStart + i];
            Cell leftCell = cells[leftStart + i];
            Cell topCell = cells[topStart + i];
            Cell rightCell = cells[rightStart + i];

            boolean dark = bottomStart + i % 2 == 0;

            addToBox(bottomCell, bottom, dark);
            addToBox(leftCell, left, dark);
            addToBox(topCell, top, dark);
            addToBox(rightCell, right, dark);
        }

        bottom.setLayoutY(boxLength);
        bottom.setLayoutX(BaseNode.HEIGHT);

        root.getChildren().addAll(top, bottom, left, right);
    }

    private void initLogo() {
        int margin = 48;

        logo = new ImageView(FXUtils.getImage("monopoly.png"));
        logo.setLayoutX(BaseNode.HEIGHT + margin);
        logo.setLayoutY(BaseNode.HEIGHT + (cellCount / 2) * BaseNode.WIDTH);
        logo.setFitWidth(BaseNode.WIDTH * cellCount - margin * 2);
        logo.setPreserveRatio(true);
        root.getChildren().add(logo);
    }

    private void initDice() {
        int margin = 48;

        die1 = new Die(true);
        die2 = new Die(false);
        die1.setLayoutX(BaseNode.HEIGHT + margin);
        die2.setLayoutX(die1.getLayoutX() + margin + die1.getWidth());
        die1.setLayoutY(boxLength - 0.5 * BaseNode.WIDTH - margin);
        die2.setLayoutY(die1.getLayoutY());
        root.getChildren().addAll(die1, die2);
    }

    private void rotateHBoxes() {
        rotateLeft();
        rotateRight();
        rotateTop();
    }

    private void addToBox(Cell cell, HBox box, boolean dark) {
        Node node = null;
        if (cell instanceof Property) {
            node = new PropertyNode((Property) cell);
        } else if (cell instanceof CC_Cell) {
            node = new CCNode((CC_Cell) cell);
        } else if (cell instanceof CornerCell) {
            node = new Rectangle(BaseNode.HEIGHT, BaseNode.HEIGHT, Color.GRAY);
            String image;
            if (cell == CornerCell.GO) {
                image = "go.png";
            } else if (cell == CornerCell.GO_JAIL) {
                image = "go_to_jail.png";
            } else if (cell == CornerCell.FREE_PARKING) {
                image = "free_parking.png";
            } else {
                image = "jail.png";
            }
            ((Rectangle) node).setFill(new ImagePattern(FXUtils.getImage(image)));
        } else {
            node = new OtherNode(cell);
        }
        if (dark) {
            node.setStyle("-fx-background-color: #ededed");
        } else {
            node.setStyle("-fx-background-color: #f6f6f6");
        }
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getSource() instanceof BaseNode) {
                BaseNode n = (BaseNode) mouseEvent.getSource();

                detailedNode.setData(n.getCell());
            }
        });
        box.getChildren().add(0, node);
    }

    private void rotateTop() {
        Rotate rotate = new Rotate();
        rotate.setPivotX((boxLength) / 2);
        rotate.setPivotY(BaseNode.HEIGHT / 2);
        top.getTransforms().add(rotate);
        rotate.setAngle(180);
    }

    private void rotateRight() {
        Rotate rotate = new Rotate();
        rotate.setPivotX(boxLength);
        rotate.setPivotY(0);
        right.getTransforms().add(rotate);
        rotate.setAngle(270);
    }

    private void rotateLeft() {
        Rotate rotate = new Rotate();
        rotate.setPivotX(0);
        rotate.setPivotY(BaseNode.HEIGHT);
        left.getTransforms().add(rotate);
        rotate.setAngle(90);
    }

    private void placeButtons() {
        buttons.setLayoutX(boxLength + 196);
        buttons.setLayoutY(boxLength);
    }

    private double[] getCoordinatesByIndex(int i) {
        i %= 40;
        Node node;
        if (i <= cellCount) {
            node = bottom.getChildren().get(cellCount - i);
        } else if (i < (cellCount + 1) * 2) {
            node = left.getChildren().get((cellCount + 1) * 2 - 1 - i);
        } else if (i < (cellCount + 1) * 3) {
            node = top.getChildren().get((cellCount + 1) * 3 - 1 - i);
        } else {
            node = right.getChildren().get((cellCount + 1) * 4 - 1 - i);
        }

        Bounds bounds = node.localToScene(node.getBoundsInLocal());
        return new double[]{bounds.getCenterX(), bounds.getCenterY()};
    }

    @Override
    public void showDiceAndMove(Function onOver, int[] numbers) {
        die1.setNumber(numbers[0]);
        die2.setNumber(numbers[1], () -> {
            markers.get(current).moveTo(getCoordinatesByIndex(current.getPosition()));
            onOver.invoke();
            rollBtn.setDisable(!current.isRollingAgain());
            detailedNode.setData(game.getCells()[current.getPosition()]);
            updateButtonStates();
        });
    }

    private void updateButtonStates() {
        Cell cell = game.getBoard().getCells()[current.getPosition()];
        if (cell instanceof PricedCell) {
            buyBtn.setDisable(((PricedCell) cell).hasOwner());
            sellBtn.setDisable(((PricedCell) cell).getOwner() != current);
            upgradeBtn.setDisable(true);

            payBtn.setDisable(!((PricedCell) cell).hasOwner() || ((PricedCell) cell).getOwner() == current);
            if (cell instanceof Property) {
                if (((Property) cell).hasOwner())
                    upgradeBtn.setDisable(!current.canUpgrade((Property) cell));
            }

            doneBtn.setDisable(!(rollBtn.isDisable() && payBtn.isDisable()));
        } else {
            doneBtn.setDisable(false);
        }
    }

    @Override
    public void receiveData(Object data) {
        markers.putAll((Map<Player, PlayerMarker>) data);
    }

    @Override
    public void initCurrentPlayer(Player player) {
        current = player;

        info.setText(String.format("%s's turn", player.getName()));
        rollBtn.setDisable(false);
        buyBtn.setDisable(true);
        sellBtn.setDisable(true);
        tradeBtn.setDisable(true);
        doneBtn.setDisable(true);
        upgradeBtn.setDisable(true);
        payBtn.setDisable(true);
    }

    @Override
    public void removePlayer(Player player) {
        root.getChildren().remove(markers.get(player));
        markers.remove(player);
    }

    @FXML
    private void onRollClick() {
        current.rollDice();
        rollBtn.setDisable(true);
    }

    @FXML
    private void onBuyClick() {
        current.buy((PricedCell) game.getBoard().getCells()[current.getPosition()]);
        updateButtonStates();
    }

    @FXML
    private void onSellClick() {
        current.sell((PricedCell) game.getBoard().getCells()[current.getPosition()]);
        updateButtonStates();
    }

    @FXML
    private void onTradeClick() {

    }

    @FXML
    private void onDoneClick() {
        game.nextPlayer();
    }

    @FXML
    private void onUpgradeClick() {
        current.upgradeProperty((Property) game.getBoard().getCells()[current.getPosition()]);
        updateButtonStates();
    }

    @FXML
    private void onPayClick() {
        current.payRentFor((PricedCell) game.getBoard().getCells()[current.getPosition()]);
        payBtn.setDisable(true);
        updateButtonStates();
    }

    @Override
    public void showMessage(String message) {
        dialogHelper.displayMessage(message);
    }

    @Override
    public boolean askForApprove(String message) {
        return dialogHelper.askForApproval(message);
    }

    @Override
    public void movePlayer(Player player) {
        markers.get(player).moveTo(getCoordinatesByIndex(player.getPosition()));
        updateButtonStates();
    }
}
