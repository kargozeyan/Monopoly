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
import monopoly.game.board.cell.*;
import monopoly.ui.custom_node.*;
import monopoly.ui.scene_controllers.BaseController;
import monopoly.utils.FXUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable, GameGUI {
    @FXML
    Pane root;

    private HBox left = new HBox();
    private HBox right = new HBox();
    private HBox top = new HBox();
    private HBox bottom = new HBox();

    private Game game = new Game(this);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add(FXUtils.getCSS("board.css"));
        Cell[] cells = game.getCells();
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

        bottom.setLayoutY(BaseNode.HEIGHT + BaseNode.WIDTH * 9);
        bottom.setLayoutX(BaseNode.HEIGHT);

        root.getChildren().addAll(top, bottom, left, right);

        rotate(cells.length / 4 - 1);

        ImageView logo = new ImageView(FXUtils.getImage("monopoly.png"));
        int margin = 48;
        logo.setLayoutX(BaseNode.HEIGHT + margin);
        logo.setLayoutY(BaseNode.HEIGHT + 4 * BaseNode.WIDTH);
        logo.setFitWidth(BaseNode.WIDTH * 9 - margin * 2);
        logo.setPreserveRatio(true);
        root.getChildren().add(logo);

        Die die1 = new Die(true);
        Die die2 = new Die(false);
        die1.setLayoutX(BaseNode.HEIGHT + margin);
        die2.setLayoutX(die1.getLayoutX() + margin + die1.getWidth());
        die1.setLayoutY(BaseNode.HEIGHT + 8.5 * BaseNode.WIDTH - margin);
        die2.setLayoutY(die1.getLayoutY());

        Button rollButton = new Button("Roll Dice");
        rollButton.setOnAction(event -> {
            die1.setNumber(5);
            die2.setNumber(6);
        });
        rollButton.setLayoutX(die2.getLayoutX() + margin + die2.getWidth());
        rollButton.setLayoutY(die2.getLayoutY());
        root.getChildren().addAll(die1, die2, rollButton);
    }

    private void rotate(int cellCount) {
        rotateLeft(cellCount);
        rotateRight(cellCount);
        rotateTop(cellCount);
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

    private void rotateTop(int cellCount) {
        Rotate rotate = new Rotate();
        rotate.setPivotX((BaseNode.HEIGHT + 9 * BaseNode.WIDTH) / 2);
        rotate.setPivotY(BaseNode.HEIGHT / 2);
        top.getTransforms().add(rotate);
        rotate.setAngle(180);
    }

    private void rotateRight(int cellCount) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(BaseNode.HEIGHT + 9 * BaseNode.WIDTH);
        rotate.setPivotY(0);
        right.getTransforms().add(rotate);
        rotate.setAngle(270);
    }

    private void rotateLeft(int cellCount) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(0);
        rotate.setPivotY(BaseNode.HEIGHT);
        left.getTransforms().add(rotate);
        rotate.setAngle(90);

    }
}
