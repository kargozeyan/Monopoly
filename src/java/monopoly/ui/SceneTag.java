package monopoly.ui;

public enum SceneTag {
    HOME("scene_home.fxml"), GAME("scene_game.fxml");
    final String fxml;

    SceneTag(String fxml) {
        this.fxml = fxml;
    }

    public String getFxml() {
        return fxml;
    }
}
