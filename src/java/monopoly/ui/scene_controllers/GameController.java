package monopoly.ui.scene_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import monopoly.game.board.Board;
import monopoly.game.board.cell.*;
import monopoly.ui.custom_node.BaseNode;
import monopoly.ui.custom_node.CCNode;
import monopoly.ui.custom_node.OtherNode;
import monopoly.ui.custom_node.PropertyNode;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends BaseController implements Initializable {
    @FXML
    Pane root;

    //    @FXML
    private HBox left = new HBox();

    //    @FXML
    private HBox right = new HBox();

    //    @FXML
    private HBox top = new HBox();

    //    @FXML
    private HBox bottom = new HBox();

    public GameController() {
        root.getScene().getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cell[] cells = new Board().getCells();
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

        root.getChildren().add(top);
        root.getChildren().add(bottom);
        root.getChildren().add(left);
        root.getChildren().add(right);

        rotate(cells.length / 4 - 1);
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
        } else {
            OtherNode other = null;
            if (cell instanceof Tax) {
                other = new OtherNode(cell.getName(), ((Tax) cell).getTax());
                other.setImg("tax_" + ((Tax) cell).getTax() + ".png");
            } else if (cell instanceof Utility) {
                other = new OtherNode(cell.getName(), ((Utility) cell).getPrice());
                String imageName = cell.getName().equals(Utility.NAME_ELECTRICITY)? "bulb.png" : "water.png";
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
