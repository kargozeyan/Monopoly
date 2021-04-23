package monopoly.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import monopoly.utils.FXUtils;

public class App extends Application {
    private SceneManager sceneManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        sceneManager = new SceneManager(stage);
        sceneManager.changeScene(SceneTag.HOME);

        stage.getIcons().add(FXUtils.getImage("icon.png"));
        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.show();
    }
}
