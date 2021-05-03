package monopoly.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import monopoly.ui.scene_controllers.BaseController;
import monopoly.ui.scene_controllers.DataReceiver;
import monopoly.utils.FXUtils;

import java.io.IOException;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(SceneTag sceneTag) {
        changeScene(sceneTag, null);
    }

    public void changeScene(SceneTag scene, Object data) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FXUtils.getFXML(scene.fxml));

        Region r = null;
        try {
            r = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseController controller = fxmlLoader.getController();

        if (data != null && controller instanceof DataReceiver) {
            ((DataReceiver) controller).receiveData(data);
        }

        controller.setSceneManager(this);
        stage.setScene(new Scene(r));
    }
}
