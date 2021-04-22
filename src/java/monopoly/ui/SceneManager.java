package monopoly.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import monopoly.ui.scene_controllers.BaseController;
import monopoly.utils.FXUtils;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(SceneTag scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FXUtils.getFXML(scene.fxml));
        Region r = fxmlLoader.load();

        BaseController controller = fxmlLoader.getController();
        controller.setSceneManager(this);
        stage.setScene(new Scene(r));
    }
}
