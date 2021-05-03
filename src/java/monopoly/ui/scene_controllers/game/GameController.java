package monopoly.ui.scene_controllers.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable, DataReceiver,GameGUI {
    @FXML
    Pane root;

    // Parts of board
    private final HBox left = new HBox();
    private final HBox right = new HBox();
    private final HBox top = new HBox();
    private final HBox bottom = new HBox();

    // dice
    private Die die1, die2;

    // markers
    private Map<Player, PlayerMarker> markers;

    private final Game game = new Game(this);
    private int cellCount; // the number of cells in one hbox, excluding corners
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adding CSS
        root.getStylesheets().add(FXUtils.getCSS("board.css"));

        // initializing board
        initBoard();
        // rotating board parts accordingly
        rotateHBoxes();

        // init logo and dice
        initLogo();
        initDice();
    }

    private void initBoard() {
        Cell[] cells = game.getCells();
        cellCount = cells.length / 4 - 1;

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

        bottom.setLayoutY(BaseNode.HEIGHT + BaseNode.WIDTH * cellCount);
        bottom.setLayoutX(BaseNode.HEIGHT);

        root.getChildren().addAll(top, bottom, left, right);
    }

    private void initLogo() {
        int margin = 48;

        ImageView logo = new ImageView(FXUtils.getImage("monopoly.png"));
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
        die1.setLayoutY(BaseNode.HEIGHT + 8.5 * BaseNode.WIDTH - margin);
        die2.setLayoutY(die1.getLayoutY());
        die1.setNumber(2);
        die2.setNumber(2);
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
        rotate.setPivotX((BaseNode.HEIGHT + cellCount * BaseNode.WIDTH) / 2);
        rotate.setPivotY(BaseNode.HEIGHT / 2);
        top.getTransforms().add(rotate);
        rotate.setAngle(180);
    }

    private void rotateRight() {
        Rotate rotate = new Rotate();
        rotate.setPivotX(BaseNode.HEIGHT + cellCount * BaseNode.WIDTH);
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

    @Override
    public void rollDice(int[] numbers) {
        die1.setNumber(numbers[0]);
        die2.setNumber(numbers[1]);
    }

    @Override
    public void receiveData(Object data) {
        markers = (Map<Player, PlayerMarker>) data;
    }
}
