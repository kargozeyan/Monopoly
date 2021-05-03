package monopoly.ui.custom_node;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import monopoly.utils.FXUtils;

import java.io.IOException;

public abstract class BaseNode extends VBox {
    // default for 2560x1440 resolution
    public static double WIDTH = 96;
    public static double HEIGHT = 164;

    public BaseNode(String fxml) {
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
}
