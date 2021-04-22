package monopoly.ui.scene_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import monopoly.ui.SceneTag;
import monopoly.utils.FXUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends BaseController implements Initializable {
    @FXML
    private VBox root;

    @FXML
    private ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add(FXUtils.getCSS("home.css"));
        img.setFitWidth(1272);
        img.setPreserveRatio(true);
        img.setImage(FXUtils.getImage("monopoly.png"));
    }

    @FXML
    private void onPlayBtnClick() throws IOException {
        sceneManager.changeScene(SceneTag.GAME);
    }

    @FXML
    private void onAboutBtnClick() {

    }

    @FXML
    private void onExitBtnClick() {
        System.exit(0);
    }
}