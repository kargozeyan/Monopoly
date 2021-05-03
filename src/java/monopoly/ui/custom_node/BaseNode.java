package monopoly.ui.custom_node;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import monopoly.utils.FXUtils;

import java.io.IOException;

public class BaseNode extends VBox {
    // default for 2560x1440 resolution
    public static int WIDTH = 96;
    public static int HEIGHT = 164;

    public static void adaptSizes(int w, int h) {
        WIDTH = w * WIDTH / 2560;
        HEIGHT = h * HEIGHT / 2560;
    }

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
