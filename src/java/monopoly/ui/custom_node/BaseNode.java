package monopoly.ui.custom_node;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import monopoly.game.board.cell.Cell;
import monopoly.ui.utils.FXUtils;

import java.io.IOException;

public abstract class BaseNode<C extends Cell> extends VBox {
    // default for 2560x1440 resolution
    public static double WIDTH = 96;
    public static double HEIGHT = 164;
    private C cell;

    public BaseNode(String fxml, C cell) {
        this.cell = cell;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXUtils.getFXML(fxml));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            setWidth(WIDTH);
            setHeight(HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public C getCell() {
        return cell;
    }
}
