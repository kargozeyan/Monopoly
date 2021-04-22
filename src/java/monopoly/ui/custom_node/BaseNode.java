package monopoly.ui.custom_node;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import monopoly.utils.FXUtils;

import java.io.IOException;

public class BaseNode extends VBox {
    public static int WIDTH = 96;
    public static int HEIGHT = 164;

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
