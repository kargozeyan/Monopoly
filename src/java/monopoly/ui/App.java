package monopoly.ui;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import monopoly.ui.custom_node.BaseNode;
import monopoly.utils.FXUtils;

public class App extends Application {
    private SceneManager sceneManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        sceneManager = new SceneManager(stage);
        sceneManager.changeScene(SceneTag.REGISTER);
        stage.getIcons().add(FXUtils.getImage("icon.png"));

        stage.show();
    }
}
