package monopoly.ui.scene_controllers.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import monopoly.game.Game;
import monopoly.game.Player;
import monopoly.game.board.cell.*;
import monopoly.ui.custom_node.*;
import monopoly.ui.scene_controllers.BaseController;
import monopoly.ui.scene_controllers.DataReceiver;
import monopoly.utils.FXUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController extends BaseController implements Initializable, DataReceiver, GameUI {
    @FXML
    Pane root;

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
    Player p1 = new Player("A1");
    Player p2 = new Player("A2");
    Player p3 = new Player("A3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adding CSS and font
        root.getStylesheets().add(FXUtils.getCSS("board.css"));
//        root.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap");

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

        initStartBtn();

        Button test = new Button("test");
        test.setLayoutX(300);
        test.setLayoutY(300);

        test.setOnAction(actionEvent -> {

        });
        root.getChildren().add(test);
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
        });
        root.getChildren().add(button);
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
            String name = cell.getName();
            String image;
            if (name.equalsIgnoreCase("go")) {
                image = "go.png";
            } else if (name.equalsIgnoreCase("go to jail")) {
                image = "go_to_jail.png";
            } else if (name.equals("Free Parking")) {
                image = "free_parking.png";
            } else {
                image = "jail.png";
            }
            ((Rectangle) node).setFill(new ImagePattern(FXUtils.getImage(image)));
        } else {
            OtherNode other = null;
            if (cell instanceof Tax) {
                other = new OtherNode(cell.getName(), ((Tax) cell).getTax());
                other.setImg("tax_" + ((Tax) cell).getTax() + ".png");
            } else if (cell instanceof Utility) {
                other = new OtherNode(cell.getName(), ((Utility) cell).getPrice());
                String imageName = cell.getName().equals(Utility.NAME_ELECTRICITY) ? "bulb.png" : "water.png";
                other.setImg(imageName);
            } else if (cell instanceof Station) {
                other = new OtherNode(cell.getName(), ((Station) cell).getPrice());
                other.setImg("train.png");
            }
            node = other;
        }
        if (dark) {
            node.setStyle("-fx-background-color: #ededed");
        } else {
            node.setStyle("-fx-background-color: #f6f6f6");
        }
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

    private double[] getCoordinatesByIndex(int i) {
        i %= 40;
        Node node;
        if (i <= cellCount) {
            node = bottom.getChildren().get(cellCount - i);
        } else if (i < (cellCount + 1) * 2) {
            node = left.getChildren().get((cellCount + 1) * 2 -1 - i);
        } else if (i < (cellCount + 1) * 3) {
            node = top.getChildren().get((cellCount + 1) * 3 - 1 - i);
        } else {
            node = right.getChildren().get((cellCount + 1) * 4 - 1 - i);
        }

        Bounds bounds = node.localToScene(node.getBoundsInLocal());
        return new double[]{bounds.getCenterX(), bounds.getCenterY()};
    }

    @Override
    public void rollDice(int[] numbers) {
        die1.setNumber(numbers[0]);
        die2.setNumber(numbers[1]);
    }

    @Override
    public void receiveData(Object data) {
        markers.putAll((Map<Player, PlayerMarker>) data);
    }
}
