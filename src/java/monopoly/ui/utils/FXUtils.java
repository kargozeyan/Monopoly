package monopoly.ui.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.net.URL;

public class FXUtils {
    // Paths of different resources
    public static String PATH_FXML = "/fxml/";
    public static String PATH_CSS = "/style/";
    public static String PATH_IMAGE = "/img/";

    // lookup a node in Region
    public static <T extends Node> T lookup(Parent root, String id) {
        return (T) root.lookup(id);
    }

    // get FXML URI by its name
    public static URL getFXML(String name) {
        return FXUtils.class.getResource(PATH_FXML + name);
    }

    // get CSS by its name
    public static String getCSS(String name) {
        return FXUtils.class.getResource(PATH_CSS + name).toExternalForm();
    }

    // get Image by its name
    public static Image getImage(String name) {
        return new Image(
                FXUtils.class.getResource(PATH_IMAGE + name).toExternalForm()
        );
    }
}
